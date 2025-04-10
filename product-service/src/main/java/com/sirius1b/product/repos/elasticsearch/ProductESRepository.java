package com.sirius1b.product.repos.elasticsearch;

import com.sirius1b.product.models.elasticsearch.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductESRepository extends ElasticsearchRepository<Product, String>{
    List<Product> findByName(String name);
//    List<Product> findByCategory(String category); // TODO: fix this
}

