package com.sirius1b.product.dtos;

import com.sirius1b.product.models.mongo.Product;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
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

    public static ProductDto fromE (com.sirius1b.product.models.elasticsearch.Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setCurrency(product.getCurrency());
        productDto.setStock(product.getStock());
        productDto.setActive(product.isActive());

        if(product.getImages() != null) {
            productDto.setImages(product.getImages().stream().map(ImageDto::fromE).toList());
        }

        if(product.getVariants() != null) {
            productDto.setVariants(product.getVariants().stream().map(VariantDto::fromE).toList());
        }

        if(product.getMultipleCurrencies() != null) {
            productDto.setMultipleCurrencies(product.getMultipleCurrencies().stream().map(CurrencyPriceDto::fromE).toList());
        }

        if(product.getCategories() != null) {
            productDto.setCategories(product.getCategories().stream().map(CategoryDto::fromE).toList());
        }


        return productDto;
    }
}



