package com.store.backend.model.request;

import lombok.Data;

@Data
public class ItemAddRequest {

    private String userId;

    private String name;
    private String description;
    private String category;
    private int yearOfUse;
    private String condition;
}
