package com.store.backend.service;

import com.store.backend.exceptions.AppException;
import com.store.backend.model.ImageData;
import com.store.backend.repository.ImageDataRepository;
import com.store.backend.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageDataService {

    @Autowired
    private ImageDataRepository repository;

    public String uploadImage(MultipartFile file,String refId) throws IOException {
        ImageData temp = ImageData.builder()
                .id(UUID.randomUUID().toString())
                .referenceId(refId)
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .data(ImageUtil.compressImage(file.getBytes())).build();
        Optional<ImageData> imageData = repository.findByReferenceId(refId);
        imageData.ifPresent(data -> temp.setId(data.getId()));
        repository.save(temp);
        return "File uploaded successfully : " + file.getOriginalFilename();
    }

    public  byte[] downloadImage(String refId) throws IOException {
        Optional<ImageData> imageData = repository.findByReferenceId(refId);
        if(imageData.isPresent()){
            byte[] images=ImageUtil.decompressImage(imageData.get().getData());
            return images;
        }
        throw new AppException("Image not present", HttpStatus.BAD_REQUEST);
    }
}
