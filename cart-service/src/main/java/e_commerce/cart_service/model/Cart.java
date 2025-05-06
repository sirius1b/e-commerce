package e_commerce.cart_service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String cartId;
    private List<CartItem> items = new ArrayList<>();
    private long total;
    
    public Cart() {}
    
    public Cart(String cartId) {
        this.cartId = cartId;
        this.total = 0;
    }
    
    public String getCartId() {
        return cartId;
    }
    
    public void setCartId(String cartId) {
        this.cartId = cartId;
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
    
    public void recalculateTotal() {
        this.total = items.stream()
            .mapToLong(item -> item.getPrice() * item.getQuantity())
            .sum();
    }
    
    public void addItem(CartItem item) {
        // Check if item already exists
        for (CartItem existingItem : items) {
            if (existingItem.getSkuId().equals(item.getSkuId())) {
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                recalculateTotal();
                return;
            }
        }
        
        // If item doesn't exist, add it
        items.add(item);
        recalculateTotal();
    }
    
    public void removeItem(String skuId) {
        items.removeIf(item -> item.getSkuId().equals(skuId));
        recalculateTotal();
    }
}