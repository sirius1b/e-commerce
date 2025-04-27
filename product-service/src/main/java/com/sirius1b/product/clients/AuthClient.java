package com.sirius1b.product.clients;

import com.sirius1b.product.configs.ServiceUrls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ServiceUrls urls;

    public Boolean isValid(String token, String role){

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-ROLE", role);
        headers.set("Authorization", token);

        return restTemplate.postForEntity(urls.auth()+"/auth/verify-token", new HttpEntity<>("", headers), Boolean.class ).getBody();
    }
}
