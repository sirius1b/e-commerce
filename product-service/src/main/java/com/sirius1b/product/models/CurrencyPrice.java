package com.sirius1b.product.models;


import com.sirius1b.product.dtos.CurrencyPriceDto;
import lombok.Data;


@Data
public class CurrencyPrice {
    private String currency;
    private double price;

    private Double originalPrice;


    public static CurrencyPrice from(CurrencyPriceDto dto){
        CurrencyPrice currencyPrice = new CurrencyPrice();
        currencyPrice.setCurrency(dto.getCurrency());
        currencyPrice.setPrice(dto.getPrice());
        currencyPrice.setOriginalPrice(dto.getOriginalPrice());
        return currencyPrice;
    }
}