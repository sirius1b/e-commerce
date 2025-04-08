package com.sirius1b.product.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

import java.util.UUID;

@Document(collection = "products")
@Data
public class Product {

    @Id
    private UUID id;
    private String name;
    private double price;
    private String currency;
    private int stock;
    private boolean isActive;
    private List<Image> images;
    private List<Variant> variants;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CurrencyPrice> multipleCurrencies;
    private List<Category> categories;
}