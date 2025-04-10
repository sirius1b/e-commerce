package com.sirius1b.product.services;


import com.sirius1b.product.dtos.ProductDto;
import com.sirius1b.product.models.elasticsearch.Product;
import com.sirius1b.product.repos.elasticsearch.CategoryESRepository;
import com.sirius1b.product.repos.elasticsearch.ProductESRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
public class IndexerService {

    @Autowired
    private CategoryESRepository categoryRepository;

    @Autowired
    private ProductESRepository productRepository;


    public void store (){
        //TODO
    }

    public void search(){
        //TODO
    }

    public List<ProductDto> getAllProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), true)
                .map(ProductDto::fromE).toList();
    }

    public ProductDto getProductById(String id) {
        Optional<Product> product = productRepository.findById(id);

        return product.map(ProductDto::fromE).orElse(null);
    }
}
