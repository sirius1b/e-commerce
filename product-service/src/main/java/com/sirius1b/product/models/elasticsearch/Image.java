package com.sirius1b.product.models.elasticsearch;


import com.sirius1b.product.dtos.ImageDto;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class Image {
    @Field(type = FieldType.Text, name = "url")
    private String url;
    @Field(type = FieldType.Text, name = "alt")
    private String alt;

    public static Image from(ImageDto dto){
        Image image = new Image();
        image.setUrl(dto.getUrl());
        image.setAlt(dto.getAlt());
        return image;
    }
}