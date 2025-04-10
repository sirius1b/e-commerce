package com.sirius1b.product.models.elasticsearch;


import com.sirius1b.product.dtos.CategoryDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.UUID;

@Data
@Document(indexName = "categories")
public class Category {
    @Id
    private String id;
    @Field(type = FieldType.Text, name = "name")
    private String name;
    @Field(type = FieldType.Text, name = "description")
    private String description;

    public static Category from(CategoryDto dto){
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }
}