package com.sirius1b.product.dtos;

import lombok.Data;

@Data
public class CurrencyPriceDto {
    private String currency;
    private double price;
    private Double originalPrice;



    public static CurrencyPriceDto from (com.sirius1b.product.models.mongo.CurrencyPrice currencyPrice){
        CurrencyPriceDto currencyPriceDto = new CurrencyPriceDto();
        currencyPriceDto.setCurrency(currencyPrice.getCurrency());
        currencyPriceDto.setPrice(currencyPrice.getPrice());
        currencyPriceDto.setOriginalPrice(currencyPrice.getOriginalPrice());
        return currencyPriceDto;
    }
}
