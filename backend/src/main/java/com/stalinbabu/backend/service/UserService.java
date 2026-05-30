package com.stalinbabu.backend.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.stalinbabu.backend.dto.LoginRequest;
import com.stalinbabu.backend.dto.ProfileResponse;
import com.stalinbabu.backend.dto.SignupRequest;
import com.stalinbabu.backend.dto.UserResponse;
import com.stalinbabu.backend.exception.EmailAlreadyExistsException;
import com.stalinbabu.backend.exception.InvalidCredentialsException;
import com.stalinbabu.backend.model.User;
import com.stalinbabu.backend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @SuppressWarnings("null")
    public UserResponse createUser(User user) {
        User saved = Objects.requireNonNull(userRepository.save(user));
        return toUserResponse(saved);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toUserResponse)
                .collect(Collectors.toList());
    }

    public Long getUserIdByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(User::getId)
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));
    }

    public String login(LoginRequest request) {

    User user =
        userRepository
        .findByEmail(
            request.getEmail()
        )
        .orElseThrow(
            () -> new InvalidCredentialsException("Invalid credentials")
        );

    if (!passwordEncoder.matches(
            request.getPassword(),
            user.getPassword()
        )
    ) {
        throw new InvalidCredentialsException("Invalid credentials");
    }

    return jwtService.generateToken(user.getEmail());

    }

    public ProfileResponse getProfile(String email) {

        User user = userRepository.findByEmail(email).orElseThrow();

        return new ProfileResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );

    }

    private UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getEloRating(),
                user.getCreatedAt()
        );
    }

    @SuppressWarnings("null")
    public UserResponse signup(SignupRequest request) {

        if (
            userRepository.existsByEmail(
                request.getEmail()
            )
        ) {

            throw new EmailAlreadyExistsException(
                "Email already exists"
            );

        }

        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(
            passwordEncoder.encode(
                request.getPassword()
            )
        );

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