package e_commerce.cart_service.dto.response;

import e_commerce.cart_service.model.Image;
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