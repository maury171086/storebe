package com.online.store.service.category;

import com.online.store.model.Category;
import com.online.store.reporsitory.CategoryRepository;
import com.online.store.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Page<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).map(categoryMapper::toDto);
    }

    @Override
    public CategoryDto createEntity(CategoryDto categoryDto) {
        Category category = categoryRepository.save(categoryMapper.toModel(categoryDto));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto updateEntity(CategoryDto categoryDto) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryDto.getId());
        if (!categoryOptional.isPresent()) {
            throw new InvalidParameterException(String.format("The Category with %d not exist.", categoryDto.getId()));
        }
        Category category = categoryOptional.get();
        category.setName(categoryDto.getName());
        category.setCover(categoryDto.getCover());
        categoryRepository.save(category);
        return categoryDto;
    }

    @Override
    public CategoryDto deleteEntity(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()) {
            throw new InvalidParameterException(String.format("The Category with %d not exist.", id));
        }
        categoryRepository.delete(categoryOptional.get());
        return categoryMapper.toDto(categoryOptional.get());
    }
}
