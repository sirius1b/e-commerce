package com.sirius1b.product.controllers;

import com.sirius1b.product.dtos.CategoryDto;
import com.sirius1b.product.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> list(){
        return ResponseEntity.ok(productService.getCategories());
    }
}
