package com.example.toukapp.dtos;

import com.example.toukapp.entity.Movie;
import com.example.toukapp.entity.Room;
import lombok.Data;
import java.sql.Date;
import java.sql.Time;

@Data
public class ScreeningRequest {
    private int screeningId;
    private Date date;
    private Time time;
    private Room room;
    private Movie movie;
}
