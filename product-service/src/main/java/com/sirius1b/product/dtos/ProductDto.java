package com.sirius1b.product.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sirius1b.product.models.mongo.Product;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {

    private String id;
    private String name;
    private double price;
    private String currency;
    private int stock;
    private boolean isActive;
    private List<ImageDto> images;
    private List<VariantDto> variants;
    private List<CurrencyPriceDto> multipleCurrencies;
    private List<CategoryDto> categories;


    public static ProductDto from (Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setCurrency(product.getCurrency());
        productDto.setStock(product.getStock());
        productDto.setActive(product.isActive());

        if(product.getImages() != null) {
            productDto.setImages(product.getImages().stream().map(ImageDto::from).toList());
        }

        if(product.getVariants() != null) {
            productDto.setVariants(product.getVariants().stream().map(VariantDto::from).toList());
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







