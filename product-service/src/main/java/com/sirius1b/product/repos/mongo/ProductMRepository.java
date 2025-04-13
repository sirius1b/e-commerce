package com.sirius1b.product.repos.mongo;

import com.sirius1b.product.models.mongo.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductMRepository extends MongoRepository<Product, String> {
}
