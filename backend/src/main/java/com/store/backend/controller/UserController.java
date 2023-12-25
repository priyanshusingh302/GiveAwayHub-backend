package com.store.backend.controller;

import com.store.backend.config.UserAuthenticationProvider;
import com.store.backend.model.User;
import com.store.backend.model.dto.UserDto;
import com.store.backend.model.request.LogInRequest;
import com.store.backend.model.request.SignUpRequest;
import com.store.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin
public class  UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody SignUpRequest user){
        UserDto userDto = userService.addUser(user);
        userDto.setToken(userAuthenticationProvider.createToken(userDto.getEmail()));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LogInRequest logInRequest) {
        UserDto userDto = userService.login(logInRequest);
        userDto.setToken(userAuthenticationProvider.createToken(userDto.getEmail()));
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping("/user/update")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return ResponseEntity.ok(userService.updateUser(user));
    }

}
