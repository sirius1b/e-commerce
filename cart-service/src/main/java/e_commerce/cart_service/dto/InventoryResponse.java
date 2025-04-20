package e_commerce.cart_service.dto;

public class InventoryResponse {
    private String status;
    private String message;
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public boolean isSuccess() {
        return "success".equals(status);
    }
}   