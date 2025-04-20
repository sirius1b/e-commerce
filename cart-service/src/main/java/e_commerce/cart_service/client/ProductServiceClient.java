package e_commerce.cart_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import e_commerce.cart_service.dto.ProductInfo;

@FeignClient(name = "product-service")
public interface ProductServiceClient {
    
    @GetMapping("/products/{skuId}")
    ProductInfo getProductInfo(@PathVariable("skuId") String skuId);
}