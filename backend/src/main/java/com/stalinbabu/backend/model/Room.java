package com.stalinbabu.backend.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomCode;

    private String status;

    private Long whitePlayerId;

    private Long blackPlayerId;

    private LocalDateTime createdAt;

    public Room() {

        this.roomCode =
                UUID.randomUUID()
                        .toString()
                        .substring(0, 6);

        this.status = "WAITING";

        this.createdAt =
                LocalDateTime.now();

    }

    public Long getId() {
        return id;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public String getStatus() {
        return status;
    }

    public Long getWhitePlayerId() {
        return whitePlayerId;
    }

    public Long getBlackPlayerId() {
        return blackPlayerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setWhitePlayerId(Long whitePlayerId) {
        this.whitePlayerId = whitePlayerId;
    }

    public void setBlackPlayerId(Long blackPlayerId) {
        this.blackPlayerId = blackPlayerId;
    }

}
