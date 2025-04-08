package com.sirius1b.product.services;

import com.sirius1b.product.models.Category;
import com.sirius1b.product.repos.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        log.info("Retrieving all categories from the repository...");
        return categoryRepository.findAll();
    }
}