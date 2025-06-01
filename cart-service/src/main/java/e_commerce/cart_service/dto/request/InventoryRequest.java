package e_commerce.cart_service.dto.request;

import java.util.List;

public class InventoryRequest {
    private List<InventoryItem> items;
    private String orderId;
    
    public InventoryRequest() {}
    
    public InventoryRequest(List<InventoryItem> items, String orderId) {
        this.items = items;
        this.orderId = orderId;
    }
    
    public List<InventoryItem> getItems() {
        return items;
    }
    
    public void setItems(List<InventoryItem> items) {
        this.items = items;
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    public static class InventoryItem {
        private String skuId;
        private int quantity;
        
        public InventoryItem() {}
        
        public InventoryItem(String skuId, int quantity) {
            this.skuId = skuId;
            this.quantity = quantity;
        }
        
        public String getSkuId() {
            return skuId;
        }
        
        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }
        
        public int getQuantity() {
            return quantity;
        }
        
        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}