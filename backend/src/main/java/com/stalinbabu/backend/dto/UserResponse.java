package com.stalinbabu.backend.dto;

import java.time.LocalDateTime;

public class UserResponse {

    private final Long id;

    private final String username;

    private final String email;

    private final Integer eloRating;

    private final LocalDateTime createdAt;

    public UserResponse(Long id, String username, String email, Integer eloRating, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.eloRating = eloRating;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Integer getEloRating() {
        return eloRating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}