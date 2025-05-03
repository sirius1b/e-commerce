package com.sirius1b.product.services;

import com.sirius1b.product.dtos.CategoryDto;
import com.sirius1b.product.dtos.ProductDto;
import com.sirius1b.product.models.Category;
import com.sirius1b.product.models.Product;
import com.sirius1b.product.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> getAllProducts(String name, String category, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Product> results ;
        if (name != null){
            results =  productRepository.findByNameContainingIgnoreCase(name, pageable);
        } else if (category != null ){
            results = productRepository.findByCategoriesNameIn(List.of(category), pageable );
        } else {
            results = productRepository.findAll(pageable);
        }

        return results.stream().map(ProductDto::from).toList();
    }

    public List<CategoryDto> getCategories() {
        List<Category> categories = new ArrayList<>();
        productRepository.findAll().forEach(p -> categories.addAll(p.getCategories()));
        return categories.stream().distinct().map(CategoryDto::from).toList();
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = Product.from(productDto);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
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