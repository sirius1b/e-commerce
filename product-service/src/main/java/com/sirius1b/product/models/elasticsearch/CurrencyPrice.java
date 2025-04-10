package com.sirius1b.product.models.elasticsearch;


import com.sirius1b.product.dtos.CurrencyPriceDto;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class CurrencyPrice {
    @Field(type = FieldType.Keyword, name = "currency")
    private String currency;
    @Field(type = FieldType.Double, name = "price")
    private double price;
    @Field(type = FieldType.Double, name = "originalPrice")
    private Double originalPrice;


    public static CurrencyPrice from(CurrencyPriceDto dto){
        CurrencyPrice currencyPrice = new CurrencyPrice();
        currencyPrice.setCurrency(dto.getCurrency());
        currencyPrice.setPrice(dto.getPrice());
        currencyPrice.setOriginalPrice(dto.getOriginalPrice());
        return currencyPrice;
    }
}