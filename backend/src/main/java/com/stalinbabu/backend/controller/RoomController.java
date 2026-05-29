package com.stalinbabu.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stalinbabu.backend.dto.JoinRoomRequest;
import com.stalinbabu.backend.model.Room;
import com.stalinbabu.backend.service.RoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(
            RoomService roomService
    ) {
        this.roomService =
                roomService;
    }

    @PostMapping("/create")
    public Room createRoom() {

        return roomService.createRoom();

    }

    @PostMapping("/join")
    public Room joinRoom(
            @Valid @RequestBody JoinRoomRequest request
    ) {

        return roomService.joinRoom(request.getRoomCode());

    }

}
