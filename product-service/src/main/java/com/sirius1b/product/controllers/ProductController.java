package com.sirius1b.product.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.sirius1b.product.dtos.ProductDto;
import com.sirius1b.product.exceptions.InternalException;
import com.sirius1b.product.services.ProductService;
import com.sirius1b.product.services.TokenVerificationService;
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
    private TokenVerificationService verificationService;


    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) throws JsonProcessingException {
        log.info("Get Products");
        List<ProductDto> products = productService.getAllProducts(name, category, page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable String id) {
        log.info("Get Product by id");
        ProductDto product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto,
                                                    @RequestHeader("Authorization") String token
                                                    ) throws JsonProcessingException, InternalException {
        log.info("create Products");
        verificationService.verifyToken(token);
        productDto.setId(UUID.randomUUID().toString());
        productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String id,
                                                    @RequestBody ProductDto productDto,
                                                    @RequestHeader("Authorization") String token) throws InternalException {
        log.info("update Products");
        verificationService.verifyToken(token);
        productDto.setId(id); // Ensure the ID from the path is used
        ProductDto updatedProduct = productService.updateProduct(productDto);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable String id,
                                                  @RequestHeader("Authorization") String token) throws InternalException {
        log.info("delete by ID, Products");
        verificationService.verifyToken(token);
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
