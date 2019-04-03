package com.online.store.service.itemImage;

import com.online.store.model.ImageGallery;
import com.online.store.model.Item;
import com.online.store.dto.ItemImageDto;
import com.online.store.reporsitory.ItemImageRepository;
import com.online.store.reporsitory.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemImageServiceImpl implements ItemImageService {

    private final Path rootLocation = Paths.get("gallery");

    @Autowired
    private ItemImageRepository itemImageRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemImageMapper itemImageMapper;

    @Override
    public Page<ItemImageDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public ItemImageDto createEntity(ItemImageDto itemImageDto) {
        Optional<Item> itemOptional = itemRepository.findById(itemImageDto.getItemId());
        if (!itemOptional.isPresent()) {
            throw new InvalidParameterException(String.format("The Item with %d not exist.", itemImageDto.getItemId()));
        }
        ImageGallery imageGallery = itemImageMapper.toModel(itemImageDto);
        imageGallery.setItem(itemOptional.get());
        itemImageRepository.save(imageGallery);
        return itemImageDto;
    }

    @Override
    public ItemImageDto updateEntity(ItemImageDto entity) {
        return null;
    }

    @Override
    public ItemImageDto deleteEntity(Long id) {
        return null;
    }

    public Resource store(MultipartFile file) {
        Resource resource;
        String fileName = "";
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(UUID.randomUUID().toString())
                    .append("_")
                    .append(file.getOriginalFilename());

            fileName = stringBuilder.toString();
            Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
        } catch (Exception e) {
            throw new RuntimeException("FAIL!", e);
        }
        try {
            Path path = rootLocation.resolve(fileName);
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!", e);
        }
        return resource;
    }

    public Resource loadFile(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }
}
