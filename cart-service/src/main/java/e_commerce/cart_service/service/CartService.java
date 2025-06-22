package e_commerce.cart_service.service;

// import e_commerce.cart_service.client.InventoryServiceClient;
import e_commerce.cart_service.client.ProductServiceClient;
import e_commerce.cart_service.dto.request.AddItemRequest;
import e_commerce.cart_service.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import e_commerce.cart_service.model.Cart;
import e_commerce.cart_service.model.CartItem;
import e_commerce.cart_service.model.ProductInfo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
public class CartService {

    // @Autowired
    // private InventoryServiceClient inventoryServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Autowired
    private CartRepository cartRepository;

    public Cart getCart(String userId) throws Exception {
        try {
            return cartRepository.findByUserId(userId);
        } catch (Exception e) {
            throw new Exception("Failed to retrieve cart for user", e);
        }
    }

    @Transactional
    public Cart addItemToCart(String userId, AddItemRequest request) throws Exception {
        // Get product info from Product Service
        ProductInfo productInfo = productServiceClient.getProductInfo(request.getSkuId());
        log.info("Product info retrieved: {}", productInfo.toString());

        // Check if the user has an active cart
        Cart cart = cartRepository.findByUserIdAndStatus(userId, "ACTIVE");
        if (cart == null) {
            // Create a new cart if no active cart exists
            log.info("Creating a new cart for user: {}", userId);
            cart = new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setUserId(userId);
            cart.setStatus("ACTIVE");
            cart.setTotal(0);
            // cart = cartRepository.save(cart);
            // log.info("New cart created: {}", cart.toString());
        }

        // Extract price (assuming you want the first currency's price)
        double price = 0;
        if (productInfo.getMultipleCurrencies() != null && !productInfo.getMultipleCurrencies().isEmpty()) {
            price = (long) productInfo.getMultipleCurrencies().get(0).getPrice();
        } else {
            throw new Exception("Product price information is missing");
        }

        // Create cart item
        CartItem cartItem = new CartItem(
            request.getSkuId(), // skuId
            productInfo.getName(),
            price,
            request.getQuantity()
        );
        cartItem.setCart(cart); //Set the cart reference into cart item
        
        log.info("Added item to cart: {}", cartItem.toString());

        // Add item to cart
        cart.addItem(cartItem);
        Cart updatedCart = cartRepository.save(cart);
        log.info("Item added to cart. Updated cart: {}", updatedCart);
        return updatedCart;
    }

    public Cart removeItemFromCart(String userId, String skuId) throws Exception {
        Cart cart = getCart(userId);

        // Remove item from cart
        cart.removeItem(skuId);
        cartRepository.save(cart);

        return cart;
    }

    public String checkoutCart(String userId) throws Exception {
        // Cart cart = getCart(userId);
        Cart cart = cartRepository.findByUserIdAndStatus(userId, "ACTIVE");
        if (cart == null || cart.getItems().isEmpty()) {
            throw new Exception("Cart is empty or does not exist.");
        }

        log.info("Checking out cart for user: {}", userId);
        
        //  Call Inventory Service to reserve/deduct stock
        //  Call Payment Service to process payment
        //  Call Order Service to create order

        cart.setStatus("INACTIVE");
        cartRepository.save(cart);

        return "Checkout successful for user: " + userId;
    }
}