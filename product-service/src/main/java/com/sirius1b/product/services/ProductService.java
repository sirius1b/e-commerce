package com.sirius1b.product.services;

import com.sirius1b.product.dtos.ProductDto;
import com.sirius1b.product.models.Product;
import com.sirius1b.product.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(ProductDto productDto) {
        Product product = convertDtoToProduct(productDto);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    public Product updateProduct(ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(productDto.getId());
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            Product updatedProduct = convertDtoToProduct(productDto);
            updatedProduct.setCreatedAt(existingProduct.getCreatedAt());
            updatedProduct.setUpdatedAt(LocalDateTime.now());
            return productRepository.save(updatedProduct);
        } else {
            return null; // or throw exception
        }
    }

    public Product getProductById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null); // or throw exception
    }

    public void deleteProductById(UUID id){
        productRepository.deleteById(id);
    }

    private Product convertDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCurrency(productDto.getCurrency());
        product.setStock(productDto.getStock());
        product.setActive(productDto.isActive());
//        product.setImages(productDto.getImages());
//        product.setVariants(productDto.getVariants());
//        product.setMultipleCurrencies(productDto.getMultipleCurrencies());
        return product;
    }
}