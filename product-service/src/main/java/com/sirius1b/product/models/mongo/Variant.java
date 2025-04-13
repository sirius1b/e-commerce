package com.sirius1b.product.models.mongo;


import com.sirius1b.product.dtos.VariantDto;
import lombok.Data;
import org.springframework.data.annotation.Id;


import java.util.Map;
import java.util.UUID;

@Data
public class Variant {
    @Id
    private UUID id;
    private String sku;
    private Map<String, String> attributes;
    private double price;
    private int stock;

    public static Variant from(VariantDto dto){
        Variant variant = new Variant();
        variant.setId(dto.getId());
        variant.setSku(dto.getSku());
        variant.setAttributes(dto.getAttributes());
        variant.setPrice(dto.getPrice());
        variant.setStock(dto.getStock());
        return variant;
    }
}