package com.sirius1b.product.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sirius1b.product.models.Product;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

    private String id;
    private String name;
    private boolean isActive;
    private List<ImageDto> images;
    private List<CurrencyPriceDto> multipleCurrencies;
    private List<CategoryDto> categories;


    public static ProductDto from (Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setActive(product.isActive());

        if(product.getImages() != null) {
            productDto.setImages(product.getImages().stream().map(ImageDto::from).toList());
        }

        if(product.getMultipleCurrencies() != null) {
            productDto.setMultipleCurrencies(product.getMultipleCurrencies().stream().map(CurrencyPriceDto::from).toList());
        }

        if(product.getCategories() != null) {
            productDto.setCategories(product.getCategories().stream().map(CategoryDto::from).toList());
        }

        return productDto;
    }


}







