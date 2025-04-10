package com.sirius1b.product.services;

import com.sirius1b.product.dtos.ProductDto;
import com.sirius1b.product.models.mongo.Product;
import com.sirius1b.product.repos.mongo.ProductMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductMRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = Product.from(productDto);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        product.setId(UUID.randomUUID().toString());
        return ProductDto.from(productRepository.save(product));
    }

    public ProductDto updateProduct(ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(productDto.getId());
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            Product updatedProduct = Product.from(productDto);
            updatedProduct.setCreatedAt(existingProduct.getCreatedAt());
            updatedProduct.setUpdatedAt(LocalDateTime.now());
            return ProductDto.from(productRepository.save(updatedProduct));
        } else {
            return null; // or throw exception
        }
    }

    public ProductDto getProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ProductDto::from).orElse(null);
    }

    public void deleteProductById(String id){
        productRepository.deleteById(id);
    }

}