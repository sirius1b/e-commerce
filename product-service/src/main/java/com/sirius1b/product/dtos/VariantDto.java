package com.sirius1b.product.dtos;

import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
class VariantDto {
    private UUID id;
    private String sku;
    private Map<String, String> attributes;
    private double price;
    private int stock;
}

