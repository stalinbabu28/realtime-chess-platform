package com.stalinbabu.backend.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.stalinbabu.backend.dto.PreferredColor;
import com.stalinbabu.backend.exception.RoomFullException;
import com.stalinbabu.backend.exception.RoomNotFoundException;
import com.stalinbabu.backend.exception.RoomNotWaitingException;
import com.stalinbabu.backend.model.Room;
import com.stalinbabu.backend.repository.RoomRepository;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final UserService userService;

    public RoomService(
            RoomRepository roomRepository,
            UserService userService
    ) {
        this.roomRepository =
                roomRepository;
        this.userService = userService;
    }

    public Room createRoom(String creatorEmail, PreferredColor preferredColor) {

        Long creatorId = userService.getUserIdByEmail(creatorEmail);

        Room room = new Room();

        switch (preferredColor) {
            case WHITE -> room.setWhitePlayerId(creatorId);
            case BLACK -> room.setBlackPlayerId(creatorId);
            case RANDOM -> {
                boolean assignWhite = ThreadLocalRandom.current().nextBoolean();
                if (assignWhite) {
                    room.setWhitePlayerId(creatorId);
                } else {
                    room.setBlackPlayerId(creatorId);
                }
            }
        }

        return roomRepository.save(room);

    }

    public Room joinRoom(String joinerEmail, String roomCode) {

        Room room = roomRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));

        if (!"WAITING".equals(room.getStatus())) {
            throw new RoomNotWaitingException("Room is not available to join");
        }

        Long joinerId = userService.getUserIdByEmail(joinerEmail);

        if (room.getWhitePlayerId() == null) {
            room.setWhitePlayerId(joinerId);
        } else if (room.getBlackPlayerId() == null) {
            room.setBlackPlayerId(joinerId);
        } else {
            throw new RoomFullException("Room is full");
        }

        room.setStatus("ACTIVE");

        return roomRepository.save(room);

    }

}
