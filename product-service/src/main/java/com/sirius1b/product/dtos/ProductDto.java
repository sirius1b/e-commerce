package com.sirius1b.product.dtos;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ProductDto {

    private UUID id;
    private String name;
    private double price;
    private String currency;
    private int stock;
    private boolean isActive;
    private List<ImageDto> images;
    private List<VariantDto> variants;
    private List<CurrencyPriceDto> multipleCurrencies;
    private List<CategoryDto> categories;
}



