package com.example.toukapp.controllers;

import com.example.toukapp.dtos.RoomRequest;
import com.example.toukapp.dtos.RoomResponse;
import com.example.toukapp.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:8001")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/api/room")
    public ResponseEntity<List<RoomResponse>> getAll() {
        return new ResponseEntity<>(roomService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/api/room")
    public ResponseEntity<HttpStatus> addRoom(@RequestBody RoomRequest roomRequest) {
        roomService.addRoom(roomRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/room/{id}")
    public ResponseEntity<RoomResponse> getRoom(@PathVariable int id){
        roomService.getRoom(id);
        return new ResponseEntity<>(roomService.getRoom(id), HttpStatus.OK);
    }

    @DeleteMapping("/api/room/{id}")
    public ResponseEntity<HttpStatus> deleteRoom(@PathVariable int id){
        roomService.deleteRoom(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/room/{id}")
    public ResponseEntity<HttpStatus> updateRoom(@PathVariable int id, @RequestBody RoomRequest roomRequest){
        roomService.updateRoom(id, roomRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}