package e_commerce.cart_service.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cart")
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cart_id")
    private String cartId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "total", nullable = false)
    private double total;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @Column(name = "status", nullable = false)
    private String status = "ACTIVE"; // Default status is ACTIVE

    public Cart() {}

    public Cart(String cartId) {
        this.cartId = cartId;
        this.total = 0;
        this.status = "ACTIVE";
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void recalculateTotal() {
        this.total = items.stream()
            .mapToDouble(item -> item.getPrice() * item.getQuantity())
            .sum();
    }

    public void addItem(CartItem item) {
        for (CartItem existingItem : items) {
            if (existingItem.getSkuId().equals(item.getSkuId())) {
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                recalculateTotal();
                return;
            }
        }
        items.add(item);
        recalculateTotal();
    }

    public void removeItem(String skuId) {
        items.removeIf(item -> item.getSkuId().equals(skuId));
        recalculateTotal();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId='" + cartId + '\'' +
                ", userId='" + userId + '\'' +
                ", total=" + total +
                ", items=" + items +
                ", status='" + status + '\'' +
                '}';
    }
}