package com.online.store.controller;

import com.online.store.service.category.CategoryService;
import com.online.store.dto.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getCategories(Pageable pageable) {
        return new ResponseEntity<>(categoryService.findAll(pageable).getContent(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.createEntity(categoryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable(value = "id") Long id, @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.updateEntity(categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(categoryService.deleteEntity(id));
    }
}
