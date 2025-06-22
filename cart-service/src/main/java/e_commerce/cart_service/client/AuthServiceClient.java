package e_commerce.cart_service.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import e_commerce.cart_service.dto.response.UserInfoDto;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
@Slf4j
@Service
public class AuthServiceClient {

    private final RestTemplate restTemplate;
    private final String authServiceUrl;

    public AuthServiceClient(RestTemplate restTemplate, 
                             @Value("${auth-service.url:http://localhost:8080}") String authServiceUrl) {
        this.restTemplate = restTemplate;
        this.authServiceUrl = authServiceUrl;
    }

    public boolean verifyToken(String token, String role){

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-ROLE", role);
        headers.set("Authorization", token);

        ResponseEntity<Boolean> response = restTemplate.postForEntity(authServiceUrl + "/auth/verify-token", new HttpEntity<>("", headers), Boolean.class);
        Boolean body = response.getBody();
        return body != null && body;
    }

    public UserInfoDto getUserInfoFromToken(String token) {
        // log.info("getUserIdFromToken called with token: {}", token);
        String url = authServiceUrl + "/user/me";
        HttpHeaders headers = new HttpHeaders();
        // if (!token.startsWith("Bearer ")) {
        //     token = "Bearer " + token;
        // }
        // log.info("updated token: {}", token);
        headers.set("Authorization", token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<UserInfoDto> response = restTemplate.exchange(url, HttpMethod.GET, entity, UserInfoDto.class);
        if(response==null || response.getBody() == null) {
            log.error("Response body is null for URL: {}", url);
            throw new RuntimeException("Failed to retrieve user info from Auth Service");
        }
        return response.getBody();
    }
}