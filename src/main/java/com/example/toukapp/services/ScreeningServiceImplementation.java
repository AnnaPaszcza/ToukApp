package com.example.toukapp.services;

import com.example.toukapp.dtos.*;
import com.example.toukapp.entity.Screening;
import com.example.toukapp.repositories.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.sql.Time;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ScreeningServiceImplementation implements ScreeningService{
    private final ScreeningRepository screeningRepository;
    private final SeatService seatService;

    @Autowired
    public ScreeningServiceImplementation(ScreeningRepository screeningRepository, SeatService seatService) {
        this.screeningRepository = screeningRepository;
        this.seatService = seatService;
    }

    @Override
    public List<ScreeningResponse> getAll() {
        return StreamSupport.stream(screeningRepository.findAll().spliterator(), false)
                .map(screeningEntity -> new ScreeningResponse(screeningEntity.getScreeningId(), screeningEntity.getDate(), screeningEntity.getTime(), screeningEntity.getRoom(), screeningEntity.getMovie()))
                .collect(Collectors.toList());
    }

    @Override
    public ScreeningRoomSeatResponse getScreening(int screeningId){
        List<ScreeningResponse> list = getAll();
        if(screeningRepository.existsById(screeningId)) {
            Optional<ScreeningResponse> foundScreening = list.stream().filter(screening -> screening.getScreeningId() == screeningId).findFirst();
            List<SeatAvailableResponse> availableSeats = seatService.getAll().stream()
                    .filter(seat -> seat.getRoom().getRoomId() == foundScreening.orElseThrow().getRoom().getRoomId())
                    .filter(seat -> !seat.getIsTaken())
                    .map(seat -> new SeatAvailableResponse(seat.getSeatId(), seat.getRow(), seat.getNumber()))
                    .toList();
            Optional<ScreeningRoomSeatResponse> screeningRoomSeatResponse = foundScreening.stream()
                    .map(screeningEntity -> new ScreeningRoomSeatResponse(screeningEntity.getScreeningId(), screeningEntity.getRoom(), availableSeats))
                    .findFirst();
            return screeningRoomSeatResponse.orElse(null);
        }
        return null;
    }

    @Override
    public ScreeningResponse findById(int screeningId){
        List<ScreeningResponse> list = getAll();
        if(screeningRepository.existsById(screeningId)) {
            Optional<ScreeningResponse> foundScreening = list.stream().filter(screening -> screening.getScreeningId() == screeningId).findFirst();
            return foundScreening.orElse(null);
        }
        return null;
    }

    @Override
    public void addScreening(ScreeningRequest screeningRequest){
        Screening screeningEntity = new Screening();
        screeningEntity.setDate(screeningRequest.getDate());
        screeningEntity.setTime(screeningRequest.getTime());
        screeningEntity.setRoom(screeningRequest.getRoom());
        screeningEntity.setMovie(screeningRequest.getMovie());
        screeningRepository.save(screeningEntity);
    }

    @Override
    public void deleteScreening(int screeningId){
        if(screeningRepository.existsById(screeningId)){
            screeningRepository.deleteById(screeningId);
        }
    }

    @Override
    public void updateScreening(int screeningId, ScreeningRequest screeningRequest){
        if(screeningRepository.existsById(screeningId)){
            Screening screeningEntity = new Screening();
            screeningEntity.setDate(screeningRequest.getDate());
            screeningEntity.setTime(screeningRequest.getTime());
            screeningEntity.setRoom(screeningRequest.getRoom());
            screeningEntity.setMovie(screeningRequest.getMovie());
            screeningRepository.save(screeningEntity);
        }
    }

    public List<ScreeningDateTimeResponse> getByDayTime(Date chosenDate, Time chosenTime){
        return StreamSupport.stream(screeningRepository.findAll().spliterator(), false)
                .filter(screeningEntity -> screeningEntity.getDate().equals(chosenDate))
                .filter(screeningEntity -> (screeningEntity.getTime().equals(chosenTime)) || (screeningEntity.getTime().after(chosenTime)))
                .sorted(Comparator.comparing(screeningEntity -> screeningEntity.getMovie().getTitle()))
                .sorted(Comparator.comparing(Screening::getTime))
                .map(screeningEntity -> new ScreeningDateTimeResponse(screeningEntity.getScreeningId(), screeningEntity.getTime(), screeningEntity.getMovie().getTitle()))
                .collect(Collectors.toList());
    }
}
