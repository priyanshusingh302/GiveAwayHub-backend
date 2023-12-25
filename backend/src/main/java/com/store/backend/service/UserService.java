package com.store.backend.service;

import com.store.backend.exceptions.AppException;
import com.store.backend.model.User;
import com.store.backend.model.dto.UserDto;
import com.store.backend.model.request.LogInRequest;
import com.store.backend.model.request.SignUpRequest;
import com.store.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDto addUser(SignUpRequest request){
        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new AppException("User Already Present", HttpStatus.BAD_REQUEST);
        }
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(request.getPassword())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();

        userRepository.save(user);

        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .id(user.getId())
                .build();
    }

    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public UserDto getUserByEmail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            User temp = user.get();
            return UserDto.builder()
                    .id(temp.getId())
                    .firstName(temp.getFirstName())
                    .lastName(temp.getLastName())
                    .email(temp.getEmail())
                    .build();
        }
        throw new AppException("Unknown User",HttpStatus.NOT_FOUND);
    }

    public User getById(String id){
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new AppException("User not Present",HttpStatus.BAD_REQUEST);
    }

    public UserDto login(LogInRequest logInRequest) {
        Optional<User> user = userRepository.findByEmail(logInRequest.getEmail());
        if(user.isEmpty()){
            throw new AppException("Unknown User",HttpStatus.BAD_REQUEST);
        }
        if(logInRequest.getPassword().equals(user.get().getPassword())){
            return UserDto.builder()
                    .id(user.get().getId())
                    .email(user.get().getEmail())
                    .firstName(user.get().getFirstName())
                    .lastName(user.get().getLastName())
                    .build();
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public User updateUser(User user) {
        Optional<User> temp = userRepository.findById(user.getId());
        if(temp.isPresent()){
            userRepository.save(user);
            return user;
        }
        throw new AppException("Unknown User",HttpStatus.NOT_FOUND);
    }
}
