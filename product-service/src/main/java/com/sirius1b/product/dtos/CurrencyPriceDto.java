package com.sirius1b.product.dtos;

import lombok.Data;

@Data
class CurrencyPriceDto {
    private String currency;
    private double price;
    private Double originalPrice;
}
