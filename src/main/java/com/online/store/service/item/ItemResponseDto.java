package com.online.store.service.item;

import com.online.store.model.ImageGallery;
import com.online.store.dto.*;

import java.util.List;

public class ItemResponseDto extends ItemDto {

    private CategoryDto category;

    private BrandDto brand;

    private List<ImageGallery> imageGalleries;

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }

    public List<ImageGallery> getImageGalleries() {
        return imageGalleries;
    }

    public void setImageGalleries(List<ImageGallery> imageGalleries) {
        this.imageGalleries = imageGalleries;
    }
}
