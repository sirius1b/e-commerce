package com.sirius1b.product.repos;

import com.sirius1b.product.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProductRepository extends MongoRepository<Product, UUID> {
}
