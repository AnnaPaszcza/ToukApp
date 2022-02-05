package com.example.toukapp.services;

import com.example.toukapp.dtos.ScreeningRequest;
import com.example.toukapp.dtos.ScreeningResponse;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface ScreeningService {
    List<ScreeningResponse> getAll();
    void addScreening(ScreeningRequest screeningRequest);
    void deleteScreening(int screeningId);
    ScreeningResponse getScreening(int screeningId);
    void updateScreening(int screeningId, ScreeningRequest screeningRequest);

    //user selects the day and the time when they would like to see the movie
    List<ScreeningResponse> getByDayTime(Date chosenDate, Time chosenTime);
}
