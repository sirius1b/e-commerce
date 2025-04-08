package com.sirius1b.product.controllers;


import com.sirius1b.product.dtos.ProductDto;
import com.sirius1b.product.models.Product;
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
    /*
    *
    - `GET /products` – List products (with filters, pagination)
    - `GET /products/{id}` – Product details
    - `POST /products` – Add a product (admin)
    - `PUT /products/{id}` – Update product
    - `DELETE /products/{id}` – Delete product
    - `GET /categories` – List categories
    * */


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        Product savedProduct = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID id, @RequestBody ProductDto productDto) {
        productDto.setId(id); // Ensure the ID from the path is used
        Product updatedProduct = productService.updateProduct(productDto);
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
        return ResponseEntity.noContent().build();
    }
}
