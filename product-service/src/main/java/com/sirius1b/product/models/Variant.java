package com.sirius1b.product.models;


import lombok.Data;

import java.util.Map;
import java.util.UUID;

@Data
class Variant {
    private UUID id;
    private String sku;
    private Map<String, String> attributes;
    private double price;
    private int stock;
}