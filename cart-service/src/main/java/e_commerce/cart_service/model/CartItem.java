package e_commerce.cart_service.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String skuId;
    private String name;
    private long price;
    private int quantity;

    public CartItem() {}
    
    public CartItem(String skuId, String name, long price, int quantity) {
        this.skuId = skuId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public String getSkuId() {
        return skuId;
    }
    
    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public long getPrice() {
        return price;
    }
    
    public void setPrice(long price) {
        this.price = price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}