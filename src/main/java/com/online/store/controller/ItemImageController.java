package com.online.store.controller;

import com.online.store.service.itemImage.ItemImageService;
import com.online.store.dto.ItemImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "v1/items/images")
public class ItemImageController {

    @Autowired
    private ItemImageService itemImageService;

    @GetMapping
    public ResponseEntity<List<ItemImageDto>> getItems(Pageable pageable) {
        return new ResponseEntity<>(itemImageService.findAll(pageable).getContent(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemImageDto> createItem(@RequestBody ItemImageDto itemImageDto) {
        return ResponseEntity.ok(itemImageService.createEntity(itemImageDto));
    }

    List<String> files = new ArrayList<>();

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            Resource resource = itemImageService.store(file);
            files.add(file.getOriginalFilename());
            return (MvcUriComponentsBuilder.fromMethodName(ItemImageController.class, "getFile", resource.getFilename()).build().toString());
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/getallfiles")
    public ResponseEntity<List<String>> getListFiles(Model model) {
        List<String> fileNames = files
                .stream().map(fileName -> MvcUriComponentsBuilder.fromMethodName(ItemImageController.class, "getFile", fileName).build().toString())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(fileNames);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = itemImageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
