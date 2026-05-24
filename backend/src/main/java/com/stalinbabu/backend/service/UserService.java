package com.stalinbabu.backend.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.stalinbabu.backend.dto.SignupRequest;
import com.stalinbabu.backend.dto.UserResponse;
import com.stalinbabu.backend.model.User;
import com.stalinbabu.backend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @SuppressWarnings("null")
    public User createUser(User user) {
        return Objects.requireNonNull(userRepository.save(user));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @SuppressWarnings("null")
    public UserResponse signup(SignupRequest request) {

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User saved = Objects.requireNonNull(userRepository.save(user));

        return new UserResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getEmail(),
                saved.getEloRating(),
                saved.getCreatedAt()
        );

    }

}