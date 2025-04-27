package com.sirius1b.product.dtos;

import com.sirius1b.product.models.Image;
import lombok.Data;


@Data
public class ImageDto {
    private String url;
    private String alt;

    public static ImageDto from (Image image){
        ImageDto imageDto = new ImageDto();
        imageDto.setUrl(imageDto.getUrl());
        imageDto.setAlt(imageDto.getAlt());
        return imageDto;
    }


}