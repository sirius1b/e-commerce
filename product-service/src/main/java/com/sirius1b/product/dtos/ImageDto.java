package com.sirius1b.product.dtos;

import com.sirius1b.product.models.mongo.Image;
import lombok.Data;


@Data
public class ImageDto {
    private String url;
    private String alt;


    public static ImageDto from (Image image){
        ImageDto imageDto = new ImageDto();
        // Assuming you can get URL and alt from the Image object
        // Replace these placeholders with the actual logic to extract the data
        imageDto.setUrl("placeholder_url");
        imageDto.setAlt("placeholder_alt");
        return imageDto;
    }

    public static ImageDto fromE (com.sirius1b.product.models.elasticsearch.Image image){
        ImageDto imageDto = new ImageDto();
        // Assuming you can get URL and alt from the Image object
        // Replace these placeholders with the actual logic to extract the data
        imageDto.setUrl("placeholder_url");
        imageDto.setAlt("placeholder_alt");
        return imageDto;
    }
}