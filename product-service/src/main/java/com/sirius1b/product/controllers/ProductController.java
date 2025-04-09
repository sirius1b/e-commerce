package com.sirius1b.product.controllers;


import com.sirius1b.product.dtos.ProductDto;
import com.sirius1b.product.models.Product;
import com.sirius1b.product.services.KafkaProducerService;
import com.sirius1b.product.services.ProductService;
import lombok.Getter;
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
    private KafkaProducerService producerService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        // TODO: read from es
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
//        Product savedProduct = productService.createProduct(productDto);
        producerService.productUpdate(productDto.toString());
        // TODO: push update to kafka for es
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID id, @RequestBody ProductDto productDto) {
        productDto.setId(id); // Ensure the ID from the path is used
        Product updatedProduct = productService.updateProduct(productDto);
        // TODO: push update to kafka for es
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable UUID id) {
        productService.deleteProductById(id);
        // TODO: update for es as well
        return ResponseEntity.noContent().build();
    }
}
