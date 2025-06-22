package e_commerce.cart_service.model;

import java.util.List;

import e_commerce.cart_service.dto.response.CategoryDto;
import e_commerce.cart_service.dto.response.CurrencyPriceDto;
import e_commerce.cart_service.dto.response.ImageDto;

public class ProductInfo {
    // private String skuId;
    // private String name;
    // private long price;

    private String id;
    private String name;
    private boolean isActive;
    private List<ImageDto> images;
    private List<CurrencyPriceDto> multipleCurrencies;
    private List<CategoryDto> categories;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    public List<ImageDto> getImages() {
        return images;
    }
    public void setImages(List<ImageDto> images) {
        this.images = images;
    }
    public List<CurrencyPriceDto> getMultipleCurrencies() {
        return multipleCurrencies;
    }
    public void setMultipleCurrencies(List<CurrencyPriceDto> multipleCurrencies) {
        this.multipleCurrencies = multipleCurrencies;
    }
    public List<CategoryDto> getCategories() {
        return categories;
    }
    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }
    
    
    
}