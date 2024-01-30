package com.store.backend.controller;

import com.store.backend.model.request.ImageUploadRequest;
import com.store.backend.service.ImageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/image")
@CrossOrigin
public class ImageController {

    @Autowired
    private ImageDataService service;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImageToBackend(@ModelAttribute ImageUploadRequest request) throws IOException {
        String response = service.uploadImage(request.getFile(), request.getReferenceId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> downloadImageFromBackend(@PathVariable String id) throws IOException {
        byte[] image = service.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }

}
