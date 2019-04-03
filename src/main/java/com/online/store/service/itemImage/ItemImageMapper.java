package com.online.store.service.itemImage;

import com.online.store.model.ImageGallery;
import org.modelmapper.ModelMapper;
import com.online.store.dto.ItemImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemImageMapper {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Convert ItemImageDto to ImageGallery
     *
     * @param itemImageDto a ItemImageDto
     * @return a ImageGallery entity
     */
    public ImageGallery toModel(ItemImageDto itemImageDto) {
        return modelMapper.map(itemImageDto, ImageGallery.class);
    }

    /**
     * Convert ImageGallery to ItemImageDto
     *
     * @param itemImage a ImageGallery
     * @return a CategoryDto entity
     */
    public ItemImageDto toDto(ImageGallery itemImage) {
        return modelMapper.map(itemImage, ItemImageDto.class);
    }
}
