package com.example.toukapp.dtos;

import com.example.toukapp.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatAvailableResponse {
    private int seatId;
    private int row;
    private int number;
}
