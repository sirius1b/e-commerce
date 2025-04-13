package com.sirius1b.product.services;

import com.sirius1b.product.models.mongo.Category;
import com.sirius1b.product.repos.mongo.CategoryMRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryService {

    @Autowired
    private CategoryMRepository categoryRepository;

    public List<Category> getAllCategories() {
        log.info("Retrieving all categories from the repository...");
        return categoryRepository.findAll();
    }
}