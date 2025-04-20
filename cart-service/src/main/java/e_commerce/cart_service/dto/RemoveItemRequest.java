package e_commerce.cart_service.dto;

public class RemoveItemRequest {
    private String skuId;
    
    public String getSkuId() {
        return skuId;
    }
    
    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
}