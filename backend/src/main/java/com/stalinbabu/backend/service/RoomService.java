package com.stalinbabu.backend.service;

import org.springframework.stereotype.Service;

import com.stalinbabu.backend.exception.RoomNotFoundException;
import com.stalinbabu.backend.exception.RoomNotWaitingException;
import com.stalinbabu.backend.model.Room;
import com.stalinbabu.backend.repository.RoomRepository;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(
            RoomRepository roomRepository
    ) {
        this.roomRepository =
                roomRepository;
    }

    public Room createRoom() {

        Room room = new Room();

        return roomRepository.save(room);

    }

    public Room joinRoom(String roomCode) {

        Room room = roomRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));

        if (!"WAITING".equals(room.getStatus())) {
            throw new RoomNotWaitingException("Room is not available to join");
        }

        room.setStatus("ACTIVE");

        return roomRepository.save(room);

    }

}
