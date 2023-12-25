package com.store.backend.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageUploadRequest {

    private String referenceId;
    private MultipartFile file;
}
