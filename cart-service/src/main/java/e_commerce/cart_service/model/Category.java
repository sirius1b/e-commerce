package e_commerce.cart_service.model;


import e_commerce.cart_service.dto.response.CategoryDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity(name = "categories")
@EqualsAndHashCode
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @EqualsAndHashCode.Exclude
    private String name;
    @EqualsAndHashCode.Exclude
    private String description;

    public static Category from(CategoryDto dto){
        Category category = new Category();
        category.setName(dto.getName().toLowerCase());
        category.setId(category.name);
        category.setDescription(dto.getDescription());
        return category;
    }

}