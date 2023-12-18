package com.store.backend.model.request;


import lombok.Data;

@Data
public class LogInRequest {

    private String email;
    private String password;
}
