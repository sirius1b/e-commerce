package com.sirius1b.product.models;


import com.sirius1b.product.dtos.CategoryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("categories")
@EqualsAndHashCode
public class Category {
    @Id
    private String id;
    @EqualsAndHashCode.Exclude
    private String name;
    @EqualsAndHashCode.Exclude
    private String description;

    public static Category from(CategoryDto dto){
        Category category = new Category();
        category.setName(dto.getName().toLowerCase());
        category.setId(category.name);
        category.setDescription(dto.getDescription());
        return category;
    }

}