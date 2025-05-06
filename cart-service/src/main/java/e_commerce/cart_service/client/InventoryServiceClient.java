package e_commerce.cart_service.client;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import e_commerce.cart_service.dto.InventoryRequest;
import e_commerce.cart_service.dto.InventoryResponse;

@Service
public class InventoryServiceClient {

    private final RestTemplate restTemplate;
    private final String inventoryServiceUrl;

    @Autowired
    public InventoryServiceClient(RestTemplate restTemplate,
                                @Value("${inventory-service.url:http://localhost:8082}") String inventoryServiceUrl) {
        this.restTemplate = restTemplate;
        this.inventoryServiceUrl = inventoryServiceUrl;
    }

    public InventoryResponse decrementInventory(InventoryRequest request) {
        String url = inventoryServiceUrl + "/inventory/decrement";
        return restTemplate.postForObject(url, request, InventoryResponse.class);
    }
}