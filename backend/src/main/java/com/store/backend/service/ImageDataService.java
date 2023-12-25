package com.store.backend.service;

import com.store.backend.exceptions.AppException;
import com.store.backend.model.ImageData;
import com.store.backend.repository.ImageDataRepository;
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

    public ImageData uploadImage(MultipartFile file,String refId) throws IOException {
        String filePath = "D:/Programing/GIT/store-backend/backend/src/main/resources/static/images/";

        ImageData imageData = new ImageData();
        imageData.setId(UUID.randomUUID().toString());
        imageData.setName(file.getOriginalFilename());
        imageData.setType(file.getContentType());
        imageData.setFilePath(filePath+refId+file.getOriginalFilename());
        imageData.setReferenceId(refId);

        file.transferTo(new File(filePath + refId + file.getOriginalFilename()));

        repository.save(imageData);

        return imageData;
    }

    public  byte[] downloadImage(String refId) throws IOException {
        Optional<ImageData> imageData = repository.findByReferenceId(refId);
        if(imageData.isPresent()){
            String filePath = imageData.get().getFilePath();
            byte[] image = Files.readAllBytes(new File(filePath).toPath());
            return image;
        }
        throw new AppException("Image not present", HttpStatus.BAD_REQUEST);
    }
}
