package com.stalinbabu.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stalinbabu.backend.model.User;
import com.stalinbabu.backend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}