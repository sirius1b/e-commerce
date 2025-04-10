package com.sirius1b.product.models.elasticsearch;

import com.sirius1b.product.dtos.ProductDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Document(indexName = "products") // Elasticsearch index
@Data
public class Product {

    @Id
    private String id;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Double, name = "price")
    private double price;

    @Field(type = FieldType.Keyword, name = "currency")
    private String currency;

    @Field(type = FieldType.Integer, name = "stock")
    private int stock;

    @Field(type = FieldType.Boolean, name = "isActive")
    private boolean isActive;

    @Field(type = FieldType.Nested, name = "images")
    private List<Image> images;

    @Field(type = FieldType.Nested, name = "variants")
    private List<Variant> variants;

    @Field(type = FieldType.Date, name = "createdAt")
    private LocalDateTime createdAt;

    @Field(type = FieldType.Date, name = "updatedAt")
    private LocalDateTime updatedAt;

    @Field(type = FieldType.Nested, name = "multipleCurrencies")
    private List<CurrencyPrice> multipleCurrencies;

    @Field(type = FieldType.Nested, name = "categories")
    private List<Category> categories;


    public static Product from(ProductDto dto){
    Product product = new Product();
    product.setId(dto.getId());
    product.setName(dto.getName());
    product.setPrice(dto.getPrice());
    product.setCurrency(dto.getCurrency());
    product.setStock(dto.getStock());
    product.setActive(dto.isActive());

    if(dto.getImages() != null) {

        product.setImages(dto.getImages().stream().map(Image::from).toList());
    }

    if(dto.getVariants() != null) {
        product.setVariants(dto.getVariants().stream().map(Variant::from).toList());
    }

//    product.setCreatedAt(dto.getCreatedAt());
//    product.setUpdatedAt(dto.getUpdatedAt());

    if(dto.getMultipleCurrencies() != null) {
        product.setMultipleCurrencies(dto.getMultipleCurrencies().stream().map(CurrencyPrice::from).toList());
    }

    if(dto.getCategories() != null) {
        product.setCategories(dto.getCategories().stream().map(Category::from).toList());
    }


    return product;
}
}