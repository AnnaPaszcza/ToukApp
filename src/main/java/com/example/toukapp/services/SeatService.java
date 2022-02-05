package com.example.toukapp.services;

import com.example.toukapp.dtos.SeatRequest;
import com.example.toukapp.dtos.SeatResponse;

import java.util.List;

public interface SeatService {
    List<SeatResponse> getAll();
    SeatResponse getSeat(int seatId);
    void addSeat(SeatRequest seatRequest);
    void deleteSeat(int seatId);
    void updateSeat(int seatId, SeatRequest seatRequest);

    //gives info about room and available seats
    List<SeatResponse> getAvailableInRoom(int roomId);
}
