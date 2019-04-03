package com.online.store.service.brand;

import com.online.store.model.Brand;
import org.modelmapper.ModelMapper;
import com.online.store.dto.BrandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrandMapper {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Convert CategoryDto to Brand
     *
     * @param brandDto a CategoryDto
     * @return a Brand entity
     */
    public Brand toModel(BrandDto brandDto) {
        return modelMapper.map(brandDto, Brand.class);
    }

    /**
     * Convert Brand to CategoryDto
     *
     * @param brand a Brand
     * @return a CategoryDto entity
     */
    public BrandDto toDto(Brand brand) {
        return modelMapper.map(brand, BrandDto.class);
    }
}
