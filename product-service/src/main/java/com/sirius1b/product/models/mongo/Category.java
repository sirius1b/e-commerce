package com.sirius1b.product.models.mongo;


import com.sirius1b.product.dtos.CategoryDto;
import lombok.Data;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document("categories")
public class Category {
    @Id
    private String id;
    private String name;
    private String description;

    public static Category from(CategoryDto dto){
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }
}