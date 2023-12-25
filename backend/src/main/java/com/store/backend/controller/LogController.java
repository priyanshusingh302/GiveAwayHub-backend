package com.store.backend.controller;

import com.store.backend.model.dto.LogResponseDto;
import com.store.backend.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/log")
@CrossOrigin
public class LogController {

    @Autowired
    private LogService logService;
    @GetMapping("/{userId}")
    ResponseEntity<LogResponseDto> getAllLogOfUser(@PathVariable String userId){
        return ResponseEntity.ok(logService.findLogByUserId(userId));
    }
}
