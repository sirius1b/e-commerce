package e_commerce.cart_service.dto.response;

import e_commerce.cart_service.model.CurrencyPrice;
import lombok.Data;

@Data
public class CurrencyPriceDto {
    private String currency;
    private double price;
    private Double originalPrice;

    public static CurrencyPriceDto from (CurrencyPrice currencyPrice){
        CurrencyPriceDto currencyPriceDto = new CurrencyPriceDto();
        currencyPriceDto.setCurrency(currencyPrice.getCurrency());
        currencyPriceDto.setPrice(currencyPrice.getPrice());
        currencyPriceDto.setOriginalPrice(currencyPrice.getOriginalPrice());
        return currencyPriceDto;
    }
}
