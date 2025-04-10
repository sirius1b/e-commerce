package com.sirius1b.product.repos.elasticsearch;


import com.sirius1b.product.models.elasticsearch.Category;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryESRepository extends ElasticsearchRepository<Category, String> {

}
