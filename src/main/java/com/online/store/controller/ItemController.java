package com.online.store.controller;

import com.online.store.service.item.ItemService;
import com.online.store.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemDto>> getItems(
            @RequestParam(name = "column", required = false) String column,
            @RequestParam(name = "search", required = false) String search,
            Pageable pageable) {
        return new ResponseEntity<>(itemService.findAllFilter(column, search, pageable).getContent(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto) {
        return ResponseEntity.ok(itemService.createEntity(itemDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable(value = "id") Long id, @RequestBody ItemDto itemDto) {
        return ResponseEntity.ok(itemService.updateEntity(itemDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemDto> deleteItem(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(itemService.deleteEntity(id));
    }
}
