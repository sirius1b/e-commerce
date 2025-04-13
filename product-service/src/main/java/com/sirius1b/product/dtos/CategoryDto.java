package com.sirius1b.product.dtos;


import com.sirius1b.product.models.mongo.Category;
import lombok.Data;

@Data
public class CategoryDto {
    private String id;
    private String name;
    private String description;

    public static CategoryDto from (Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }


}