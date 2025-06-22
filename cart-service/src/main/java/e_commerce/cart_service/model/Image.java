package e_commerce.cart_service.model;


import e_commerce.cart_service.dto.response.ImageDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "images")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String alt;

    public static Image from(ImageDto dto){
        Image image = new Image();
        image.setUrl(dto.getUrl());
        image.setAlt(dto.getAlt());
        return image;
    }
}