package com.example.time_logging.services;

import com.example.time_logging.dto.UserDto;
import com.example.time_logging.entities.User;
import com.example.time_logging.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;


    }
}
