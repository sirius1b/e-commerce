package com.sirius1b.product.models;


import lombok.Data;

@Data
class CurrencyPrice {
    private String currency;
    private double price;
    private Double originalPrice;
}