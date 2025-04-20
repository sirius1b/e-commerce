package e_commerce.cart_service.util;

import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    
    // In a real application, this would verify the JWT and extract claims
    public String getUserIdFromToken(String token) {
        // Remove "Bearer " prefix if present
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        
        // For demonstration purposes, we'll extract a mock user ID
        // In a real application, you would decode and verify the JWT
        return "user_" + token.hashCode();
    }
}