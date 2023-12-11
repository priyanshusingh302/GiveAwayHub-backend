package com.store.backend.controller;

import com.store.backend.model.Task;
import com.store.backend.model.User;
import com.store.backend.model.request.EmailRequest;
import com.store.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class  UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        return userService.addUser(user);
    }

    public List<User> getUsers(){
        return userService.findAllUser();
    }

    @PostMapping("/get-by-email")
    public User getUserByEmail(@RequestBody EmailRequest emailRequest){
        return userService.getUserByEmail(emailRequest.getEmail());
    }
}
