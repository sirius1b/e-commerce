package e_commerce.cart_service.dto.response;


import e_commerce.cart_service.model.Category;
import lombok.Data;

@Data
public class CategoryDto {
    private String name;
    private String description;

    public static CategoryDto from (Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        return categoryDto;
    }


}