package com.online.store.service.itemImage;

import com.online.store.service.GenericService;
import com.online.store.dto.ItemImageDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ItemImageService extends GenericService<ItemImageDto> {
    Resource store(MultipartFile file);
    Resource loadFile(String filename);
}
