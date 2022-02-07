package com.example.toukapp.dtos;

import com.example.toukapp.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreeningDateTimeResponse {
    private int screeningId;
    private Time time;
    private String movieTitle;
}
