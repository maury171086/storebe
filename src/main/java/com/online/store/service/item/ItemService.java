package com.online.store.service.item;

import com.online.store.service.GenericService;
import org.springframework.data.domain.Page;
import com.online.store.dto.ItemDto;
import org.springframework.data.domain.Pageable;

public interface ItemService extends GenericService<ItemDto> {
    Page<ItemDto> findAllFilter(String column, String search, Pageable pageable);
}
