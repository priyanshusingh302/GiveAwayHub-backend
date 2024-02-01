package com.store.backend.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ItemAddRequest {

    private String userId;

    private String name;
    private String description;
    private String category;
    private int yearOfUse;
    private String condition;
    private MultipartFile file;
}
