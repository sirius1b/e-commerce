package e_commerce.cart_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import e_commerce.cart_service.dto.InventoryRequest;
import e_commerce.cart_service.dto.InventoryResponse;

@FeignClient(name = "inventory-service")
public interface InventoryServiceClient {
    
    @PostMapping("/inventory/decrement")
    InventoryResponse decrementInventory(@RequestBody InventoryRequest request);
}