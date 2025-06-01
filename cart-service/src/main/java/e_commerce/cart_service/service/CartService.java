package e_commerce.cart_service.service;

// import e_commerce.cart_service.client.InventoryServiceClient;
import e_commerce.cart_service.client.ProductServiceClient;
import e_commerce.cart_service.dto.request.AddItemRequest;
import e_commerce.cart_service.dto.request.RemoveItemRequest;
import e_commerce.cart_service.repository.CartRepository;
import e_commerce.cart_service.model.Cart;
import e_commerce.cart_service.model.CartItem;
import e_commerce.cart_service.model.ProductInfo;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
        // Check if the user has an active cart
        Cart cart = cartRepository.findByUserIdAndStatus(userId, "ACTIVE");
        if (cart == null) {
            // Create a new cart if no active cart exists
            cart = new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setUserId(userId);
            cart.setStatus("ACTIVE");
            cart.setTotal(0);
            cartRepository.save(cart);
        }
    
        // Create cart item
        CartItem cartItem = new CartItem(
            productInfo.getSkuId(),
            productInfo.getName(),
            productInfo.getPrice(),
            request.getQuantity()
        );
    
        // Check if stock is available with Inventory Service
        // Uncomment the following lines if you have an InventoryServiceClient implemented
        
        // boolean isStockAvailable = inventoryServiceClient.checkStock(request.getSkuId(), request.getQuantity());
        // if (!isStockAvailable) {
        //     throw new Exception("Insufficient stock for item: " + request.getSkuId());
        // }

    
        // Add item to cart
        cart.addItem(cartItem);
        cartRepository.save(cart);
    
        return cart;
    }

    public Cart removeItemFromCart(String userId, String skuId) throws Exception {
        Cart cart = getCart(userId);

        // Remove item from cart
        cart.removeItem(skuId);
        cartRepository.save(cart);

        return cart;
    }

    public String checkoutCart(String userId) throws Exception {
        Cart cart = getCart(userId);
    
        // Perform checkout logic (e.g., payment processing, inventory update)
        cart.setStatus("INACTIVE"); // Mark the cart as inactive
        cartRepository.save(cart); // Save the updated cart
    
        return "Checkout successful for user: " + userId;
    }
}