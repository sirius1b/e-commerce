package com.sirius1b.product.dtos;


import com.sirius1b.product.models.Category;
import lombok.Data;

@Data
public class CategoryDto {
    private String name;
    private String description;

    public static CategoryDto from (Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }


}