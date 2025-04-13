package com.sirius1b.product.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sirius1b.product.dtos.ProductDto;
import com.sirius1b.product.services.EventProducerService;
import com.sirius1b.product.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private EventProducerService producerService;



    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() throws JsonProcessingException {
        log.info("Get Products");
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) throws JsonProcessingException {
        log.info("create Products");
        productDto.setId(UUID.randomUUID().toString());
        ProductDto savedProduct = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        log.info("update Products");
        productDto.setId(id); // Ensure the ID from the path is used
        ProductDto updatedProduct = productService.updateProduct(productDto);
        if (updatedProduct != null) {
            producerService.productUpdate(updatedProduct.toString());
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable String id) {
        log.info("Get Product by id");
        ProductDto product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            product = productService.getProductById(id);
            if (product != null){
                return ResponseEntity.ok(product);
            }
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable String id) {
        log.info("delete by ID, Products");
        productService.deleteProductById(id);
        producerService.productDelete(id.toString());
        return ResponseEntity.noContent().build();
    }
}
