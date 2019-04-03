package com.online.store.service.item;

import com.online.store.model.*;
import com.online.store.reporsitory.*;
import com.online.store.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Page<ItemDto> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable).map(itemMapper::toItemResponseDto);
    }

    @Override
    public Page<ItemDto> findAllFilter(String column, String search, Pageable pageable) {
        if (null != column && null != search) {
            if (column.equals("CATEGORY")) {
                return itemRepository.findAllByCategoryName(pageable, search).map(itemMapper::toItemResponseDto);
            }
            if (column.equals("BRAND")) {
                return itemRepository.findAllByBrandName(pageable, search).map(itemMapper::toItemResponseDto);
            }
            if (column.equals("PRICE")) {
                return itemRepository.findAllByPrice(pageable, Double.valueOf(search)).map(itemMapper::toItemResponseDto);
            }
            if (column.equals("NAME")) {
                return itemRepository.findAllByNameContainsIgnoreCase(pageable, search).map(itemMapper::toItemResponseDto);
            }
            if (column.equals("CAPACITY")) {
                return itemRepository.findAllByCapacityContainsIgnoreCase(pageable, search).map(itemMapper::toItemResponseDto);
            }
        }
        return findAll(pageable);
    }

    @Override
    public ItemDto createEntity(ItemDto itemDto) {
        Optional<Category> categoryOptional = categoryRepository.findById(itemDto.getCategoryId());
        if (!categoryOptional.isPresent()) {
            throw new InvalidParameterException(String.format("The Category with %d not exist.", itemDto.getCategoryId()));
        }
        Optional<Brand> brandOptional = brandRepository.findById(itemDto.getBrandId());
        if (!brandOptional.isPresent()) {
            throw new InvalidParameterException(String.format("The Brand with %d not exist.", itemDto.getBrandId()));
        }
        Item item = itemMapper.toModel(itemDto);
        item.setCategory(categoryOptional.get());
        item.setBrand(brandOptional.get());
        itemRepository.save(item);
        return itemMapper.toDto(item);
    }

    @Override
    public ItemDto updateEntity(ItemDto itemDto) {
        Optional<Item> itemOptional = itemRepository.findById(itemDto.getId());
        if (!itemOptional.isPresent()) {
            throw new InvalidParameterException(String.format("The Item with %d does not exist.", itemDto.getId()));
        }
        Optional<Category> categoryOptional = categoryRepository.findById(itemDto.getCategoryId());
        if (!categoryOptional.isPresent()) {
            throw new InvalidParameterException(String.format("The Category with %d does not exist.", itemDto.getCategoryId()));
        }
        Optional<Brand> brandOptional = brandRepository.findById(itemDto.getBrandId());
        if (!brandOptional.isPresent()) {
            throw new InvalidParameterException(String.format("The Brand with %d does not exist.", itemDto.getBrandId()));
        }

        Item item = itemOptional.get();
        item.setCategory(categoryOptional.get());
        item.setBrand(brandOptional.get());
        item.setName(itemDto.getName());
        item.setCapacity(itemDto.getCapacity());
        item.setDescription(itemDto.getDescription());
        itemRepository.save(item);
        return itemDto;
    }

    @Override
    public ItemDto deleteEntity(Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (!itemOptional.isPresent()) {
            throw new InvalidParameterException(String.format("The Item with %d does not exist.", id));
        }
        itemRepository.delete(itemOptional.get());
        return itemMapper.toDto(itemOptional.get());
    }
}
