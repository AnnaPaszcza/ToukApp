package com.example.toukapp.dtos;

import com.example.toukapp.entity.Movie;
import com.example.toukapp.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Time;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningRoomSeatResponse {
    private int screeningId;
    private Room room;
    private List<SeatAvailableResponse> availableSeats;
}
