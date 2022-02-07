package com.example.toukapp.dtos;

import com.example.toukapp.entity.Room;
import lombok.Data;

@Data
public class SeatRequest {
    private int seatId;
    private int row;
    private int number;
    private Boolean isTaken;
    private Room room;
}
