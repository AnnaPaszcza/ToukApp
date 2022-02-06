package com.example.toukapp.services;

import com.example.toukapp.dtos.ScreeningDateTimeResponse;
import com.example.toukapp.dtos.ScreeningRequest;
import com.example.toukapp.dtos.ScreeningResponse;
import com.example.toukapp.dtos.ScreeningRoomSeatResponse;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
public interface ScreeningService {
    List<ScreeningResponse> getAll();
    void addScreening(ScreeningRequest screeningRequest);
    void deleteScreening(int screeningId);
    ScreeningRoomSeatResponse getScreening(int screeningId);
    void updateScreening(int screeningId, ScreeningRequest screeningRequest);
    List<ScreeningDateTimeResponse> getByDayTime(Date chosenDate, Time chosenTime);
}
