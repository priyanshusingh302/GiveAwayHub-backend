package com.store.backend.service;

import com.store.backend.model.User;
import com.store.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        user.setId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
