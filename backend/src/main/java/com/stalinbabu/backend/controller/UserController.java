package com.stalinbabu.backend.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stalinbabu.backend.dto.ProfileResponse;
import com.stalinbabu.backend.dto.UserResponse;
import com.stalinbabu.backend.model.User;
import com.stalinbabu.backend.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/profile")
    public ProfileResponse profile(Authentication authentication) {
        return userService.getProfile(authentication.getName());
    }

}