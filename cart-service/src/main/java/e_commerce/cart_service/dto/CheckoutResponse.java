package e_commerce.cart_service.dto;

public class CheckoutResponse {
    private String status;
    private String checkoutToken;
    
    public CheckoutResponse() {}
    
    public CheckoutResponse(String status, String checkoutToken) {
        this.status = status;
        this.checkoutToken = checkoutToken;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getCheckoutToken() {
        return checkoutToken;
    }
    
    public void setCheckoutToken(String checkoutToken) {
        this.checkoutToken = checkoutToken;
    }
}