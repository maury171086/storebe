package com.online.store.service.category;

import com.online.store.model.Category;
import org.modelmapper.ModelMapper;
import com.online.store.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Convert CategoryDto to Category
     *
     * @param categoryDto a CategoryDto
     * @return a Category entity
     */
    public Category toModel(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

    /**
     * Convert Category to CategoryDto
     *
     * @param category a Category
     * @return a CategoryDto entity
     */
    public CategoryDto toDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }
}
