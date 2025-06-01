package e_commerce.cart_service.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Service
public class AuthServiceClient {

    private final RestTemplate restTemplate;
    private final String authServiceUrl;

    public AuthServiceClient(RestTemplate restTemplate, 
                             @Value("${auth-service.url:http://localhost:8080}") String authServiceUrl) {
        this.restTemplate = restTemplate;
        this.authServiceUrl = authServiceUrl;
    }

    public boolean verifyToken(String token) {
        String url = authServiceUrl + "/auth/verify-token";
        return restTemplate.postForObject(url, token, Boolean.class);
    }

    public String getUserIdFromToken(String token) {
        String url = authServiceUrl + "/users/me";
        return restTemplate.getForObject(url + "?token=" + token, String.class);
    }
}