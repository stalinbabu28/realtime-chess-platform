package com.stalinbabu.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stalinbabu.backend.dto.LoginRequest;
import com.stalinbabu.backend.dto.SignupRequest;
import com.stalinbabu.backend.dto.UserResponse;
import com.stalinbabu.backend.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserResponse signup(
            @RequestBody SignupRequest request
    ) {

        return userService.signup(request);

    }

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request
    ) {

        return userService.login(request);

    }

}