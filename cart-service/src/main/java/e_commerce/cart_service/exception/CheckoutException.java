package e_commerce.cart_service.exception;

public class CheckoutException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public CheckoutException(String message) {
        super(message);
    }
}