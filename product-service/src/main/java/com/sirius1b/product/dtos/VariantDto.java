package com.sirius1b.product.dtos;

import com.sirius1b.product.models.mongo.Variant;
import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
public class VariantDto {
    private UUID id;
    private String sku;
    private Map<String, String> attributes;
    private double price;
    private int stock;


    public static VariantDto from (Variant variant){
        VariantDto variantDto = new VariantDto();
        variantDto.setId(variant.getId());
        variantDto.setSku(variant.getSku());
        variantDto.setAttributes(variant.getAttributes());
        variantDto.setPrice(variant.getPrice());
        variantDto.setStock(variant.getStock());
        return variantDto;
    }

    public static VariantDto fromE (com.sirius1b.product.models.elasticsearch.Variant variant){
        VariantDto variantDto = new VariantDto();
        variantDto.setId(variant.getId());
        variantDto.setSku(variant.getSku());
        variantDto.setAttributes(variant.getAttributes());
        variantDto.setPrice(variant.getPrice());
        variantDto.setStock(variant.getStock());
        return variantDto;
    }
}

