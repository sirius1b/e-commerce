package com.sirius1b.product.repos;

import com.sirius1b.product.models.Category;
import com.sirius1b.product.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {


    @Query("{ 'categories.name': { $in: ?0 } }")
    Page<Product> findByCategoriesNameIn(List<String> categoryNames, Pageable pageable);

    // Example for finding products containing ALL specified category names
    @Query("{ 'categories': { $all: ?0 } }")
    Page<Product> findByCategoriesContainsAll(List<Category> categories, Pageable pageable);

    // Example for finding products where name contains (case-insensitive)
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Example combining name filter and category name filter
    @Query("{ 'name': { $regex: ?0, $options: 'i' }, 'categories.name': { $in: ?1 } }")
    Page<Product> findByNameContainingIgnoreCaseAndCategoriesNameIn(String name, List<String> categoryNames, Pageable pageable);


}
