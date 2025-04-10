package com.sirius1b.product.models.elasticsearch;


import com.sirius1b.product.dtos.VariantDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Map;
import java.util.UUID;

@Data
public class Variant {
    @Id
    private UUID id;
    @Field(type = FieldType.Keyword, name = "sku")
    private String sku;
    @Field(type = FieldType.Object, name = "attributes")
    private Map<String, String> attributes;
    @Field(type = FieldType.Double, name = "price")
    private double price;
    @Field(type = FieldType.Integer, name = "stock")
    private int stock;

    public static Variant from(VariantDto dto){
        Variant variant = new Variant();
        variant.setId(dto.getId());
        variant.setSku(dto.getSku());
        variant.setAttributes(dto.getAttributes());
        variant.setPrice(dto.getPrice());
        variant.setStock(dto.getStock());
        return variant;
    }
}