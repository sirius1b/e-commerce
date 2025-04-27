package com.sirius1b.product.models;


import com.sirius1b.product.dtos.ImageDto;
import lombok.Data;


@Data
public class Image {
    private String url;
    private String alt;

    public static Image from(ImageDto dto){
        Image image = new Image();
        image.setUrl(dto.getUrl());
        image.setAlt(dto.getAlt());
        return image;
    }
}