package e_commerce.cart_service.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import e_commerce.cart_service.model.ProductInfo;

import org.springframework.beans.factory.annotation.Value;

@Service
public class ProductServiceClient {
    
    private final RestTemplate restTemplate;
    private final String productServiceUrl;
    
    public ProductServiceClient(RestTemplate restTemplate, 
                               @Value("${product-service.url:http://localhost:8081}") String productServiceUrl) {
        this.restTemplate = restTemplate;
        this.productServiceUrl = productServiceUrl;
    }
    
    public ProductInfo getProductInfo(String skuId) {
        String url = productServiceUrl + "/products/" + skuId;
        return restTemplate.getForObject(url, ProductInfo.class);
    }
}