package com.example.time_logging.controllers;

import com.example.time_logging.dto.UserDto;
import com.example.time_logging.entities.User;
import com.example.time_logging.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/v1/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
