package com.example.toukapp.services;

import com.example.toukapp.dtos.RoomRequest;
import com.example.toukapp.dtos.RoomResponse;

import java.util.List;

public interface RoomService {
    List<RoomResponse> getAll();
    RoomResponse getRoom(int roomId);
    void addRoom(RoomRequest roomRequest);
    void deleteRoom(int roomId);
    void updateRoom(int roomId, RoomRequest roomRequest);
}
