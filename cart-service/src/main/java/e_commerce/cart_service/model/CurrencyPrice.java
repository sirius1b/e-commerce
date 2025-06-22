package e_commerce.cart_service.model;


import e_commerce.cart_service.dto.response.CurrencyPriceDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class CurrencyPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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