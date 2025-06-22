package e_commerce.cart_service.model;

import e_commerce.cart_service.dto.response.ProductDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "products")
@Data
public class Product {

    @Id
    private String id;

    private String name;

    private boolean isActive;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> images;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CurrencyPrice> multipleCurrencies;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Category> categories;

    public static Product from(ProductDto dto){
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());

        product.setActive(dto.isActive());

        if(dto.getImages() != null) {

            product.setImages(dto.getImages().stream().map(Image::from).toList());
        }

        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(product.getCreatedAt());

        if(dto.getMultipleCurrencies() != null) {
            product.setMultipleCurrencies(dto.getMultipleCurrencies().stream().map(CurrencyPrice::from).toList());
        }

        if(dto.getCategories() != null) {
            product.setCategories(dto.getCategories().stream().map(Category::from).toList());
        }


        return product;
    }
}