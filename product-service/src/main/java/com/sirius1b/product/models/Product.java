package com.sirius1b.product.models;

import com.sirius1b.product.dtos.ProductDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "products")
@Data
public class Product {

    @Id
    private String id;

    private String name;

    private boolean isActive;

    private List<Image> images;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<CurrencyPrice> multipleCurrencies;

    private List<Category> categories;

    public static Product from(ProductDto dto){
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());

        product.setActive(dto.isActive());

        if(dto.getImages() != null) {

            product.setImages(dto.getImages().stream().map(Image::from).toList());
        }

        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(product.getCreatedAt());

        if(dto.getMultipleCurrencies() != null) {
            product.setMultipleCurrencies(dto.getMultipleCurrencies().stream().map(CurrencyPrice::from).toList());
        }

        if(dto.getCategories() != null) {
            product.setCategories(dto.getCategories().stream().map(Category::from).toList());
        }


        return product;
    }
}