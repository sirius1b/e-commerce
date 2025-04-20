package e_commerce.cart_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import e_commerce.cart_service.client.InventoryServiceClient;
import e_commerce.cart_service.client.ProductServiceClient;
import e_commerce.cart_service.dto.AddItemRequest;
import e_commerce.cart_service.dto.CheckoutResponse;
import e_commerce.cart_service.dto.InventoryRequest;
import e_commerce.cart_service.dto.InventoryResponse;
import e_commerce.cart_service.dto.ProductInfo;
import e_commerce.cart_service.dto.InventoryRequest.InventoryItem;
import e_commerce.cart_service.exception.CartEmptyException;
import e_commerce.cart_service.exception.CheckoutException;
import e_commerce.cart_service.model.Cart;
import e_commerce.cart_service.model.CartItem;
import e_commerce.cart_service.util.JwtUtils;

@Service
public class CartService {
    
    private static final String CART_KEY_PREFIX = "cart:";
    
    @Autowired
    private RedisTemplate<String, Cart> redisTemplate;
    
    @Autowired
    private ProductServiceClient productServiceClient;
    
    @Autowired
    private InventoryServiceClient inventoryServiceClient;
    
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    
    @Autowired
    private JwtUtils jwtUtils;
    
    public Cart getCart(String token) {
        String userId = jwtUtils.getUserIdFromToken(token);
        String cartKey = CART_KEY_PREFIX + userId;
        
        Cart cart = redisTemplate.opsForValue().get(cartKey);
        if (cart == null) {
            cart = new Cart(UUID.randomUUID().toString());
            redisTemplate.opsForValue().set(cartKey, cart);
        }
        
        return cart;
    }
    
    public Cart addItemToCart(String token, AddItemRequest request) {
        String userId = jwtUtils.getUserIdFromToken(token);
        String cartKey = CART_KEY_PREFIX + userId;
        
        // Get product info from Product Service
        ProductInfo productInfo = productServiceClient.getProductInfo(request.getSkuId());
        
        // Get or create cart
        Cart cart = getCart(token);
        
        // Create cart item
        CartItem cartItem = new CartItem(
            productInfo.getSkuId(),
            productInfo.getName(),
            productInfo.getPrice(),
            request.getQuantity()
        );
        
        // Add item to cart
        cart.addItem(cartItem);
        
        // Save updated cart
        redisTemplate.opsForValue().set(cartKey, cart);
        
        return cart;
    }
    
    public Cart removeItemFromCart(String token, String skuId) {
        String userId = jwtUtils.getUserIdFromToken(token);
        String cartKey = CART_KEY_PREFIX + userId;
        
        // Get cart
        Cart cart = getCart(token);
        
        // Remove item
        cart.removeItem(skuId);
        
        // Save updated cart
        redisTemplate.opsForValue().set(cartKey, cart);
        
        return cart;
    }
    
    public CheckoutResponse checkout(String token) {
        String userId = jwtUtils.getUserIdFromToken(token);
        String cartKey = CART_KEY_PREFIX + userId;
        
        // Get cart
        Cart cart = getCart(token);
        
        // Validate cart is not empty
        if (cart.getItems().isEmpty()) {
            throw new CartEmptyException("Cart is empty");
        }
        
        // Validate prices from Product Service
        validatePrices(cart);
        
        // Generate order ID
        String orderId = "order_" + UUID.randomUUID().toString();
        
        // Call Inventory Service to decrement stock
        InventoryResponse response = decrementInventory(cart, orderId);
        
        if (!response.isSuccess()) {
            throw new CheckoutException(response.getMessage());
        }
        
        // Generate checkout token
        String checkoutToken = "chk_" + UUID.randomUUID().toString();
        
        // Emit cart.checkout.initiated event
        emitCheckoutEvent(cart, orderId, checkoutToken, userId);
        
        // Clear cart after successful checkout
        redisTemplate.delete(cartKey);
        
        return new CheckoutResponse("checkout_initiated", checkoutToken);
    }
    
    private void validatePrices(Cart cart) {
        for (CartItem item : cart.getItems()) {
            ProductInfo productInfo = productServiceClient.getProductInfo(item.getSkuId());
            
            // Validate price hasn't changed
            if (productInfo.getPrice() != item.getPrice()) {
                // Update to current price
                item.setPrice(productInfo.getPrice());
            }
        }
        
        // Recalculate total
        cart.recalculateTotal();
    }
    
    private InventoryResponse decrementInventory(Cart cart, String orderId) {
        List<InventoryItem> items = cart.getItems().stream()
            .map(item -> new InventoryItem(item.getSkuId(), item.getQuantity()))
            .collect(Collectors.toList());
        
        InventoryRequest request = new InventoryRequest(items, orderId);
        
        return inventoryServiceClient.decrementInventory(request);
    }
    
    private void emitCheckoutEvent(Cart cart, String orderId, String checkoutToken, String userId) {
        // Create event payload
        CheckoutEvent event = new CheckoutEvent();
        event.setCartId(cart.getCartId());
        event.setOrderId(orderId);
        event.setCheckoutToken(checkoutToken);
        event.setUserId(userId);
        event.setItems(new ArrayList<>(cart.getItems()));
        event.setTotal(cart.getTotal());
        
        // Send event to Kafka
        kafkaTemplate.send("cart.checkout.initiated", event);
    }
    
    // Inner class for checkout event
    public static class CheckoutEvent {
        private String cartId;
        private String orderId;
        private String checkoutToken;
        private String userId;
        private List<CartItem> items;
        private long total;
        
        public String getCartId() {
            return cartId;
        }
        
        public void setCartId(String cartId) {
            this.cartId = cartId;
        }
        
        public String getOrderId() {
            return orderId;
        }
        
        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
        
        public String getCheckoutToken() {
            return checkoutToken;
        }
        
        public void setCheckoutToken(String checkoutToken) {
            this.checkoutToken = checkoutToken;
        }
        
        public String getUserId() {
            return userId;
        }
        
        public void setUserId(String userId) {
            this.userId = userId;
        }
        
        public List<CartItem> getItems() {
            return items;
        }
        
        public void setItems(List<CartItem> items) {
            this.items = items;
        }
        
        public long getTotal() {
            return total;
        }
        
        public void setTotal(long total) {
            this.total = total;
        }
    }
}