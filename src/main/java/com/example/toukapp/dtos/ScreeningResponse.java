package com.example.toukapp.dtos;

import com.example.toukapp.entity.Movie;
import com.example.toukapp.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningResponse {
    private int screeningId;
    private Date date;
    private Time time;
    private Room room;
    private Movie movie;
}
