package com.sirius1b.product.repos;

import com.sirius1b.product.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface CategoryRepository extends MongoRepository<Category, UUID> {
}
