package com.example.toukapp.services;

import com.example.toukapp.dtos.ScreeningRequest;
import com.example.toukapp.dtos.ScreeningResponse;
import com.example.toukapp.services.SeatService;
import com.example.toukapp.entity.Screening;
import com.example.toukapp.repositories.ScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.sql.Time;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ScreeningServiceImplementation implements ScreeningService{
    private final ScreeningRepository screeningRepository;

    @Autowired
    public ScreeningServiceImplementation(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    @Override
    public List<ScreeningResponse> getAll() {
        return StreamSupport.stream(screeningRepository.findAll().spliterator(), false)
                .map(screeningEntity -> new ScreeningResponse(screeningEntity.getScreeningId(), screeningEntity.getDate(), screeningEntity.getTime(), screeningEntity.getRoom(), screeningEntity.getMovie()))
                .collect(Collectors.toList());
    }

    @Override
    public ScreeningResponse getScreening(int screeningId){
        List<ScreeningResponse> list = getAll();
        if(screeningRepository.existsById(screeningId)){
            return list.stream().filter(screening -> screening.getScreeningId() == screeningId)
                    .findAny()
                    .get();
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

    public List<ScreeningResponse> getByDayTime(Date chosenDate, Time chosenTime){
        List<ScreeningResponse> screeningResponses = StreamSupport.stream(screeningRepository.findAll().spliterator(), false)
                .filter(screeningEntity -> screeningEntity.getDate().equals(chosenDate))
                .filter(screeningEntity -> (screeningEntity.getTime().equals(chosenTime)) || (screeningEntity.getTime().after(chosenTime)))
                .sorted(Comparator.comparing(screeningEntity -> screeningEntity.getMovie().getTitle()))
                .sorted(Comparator.comparing(Screening::getTime))
                .map(screeningEntity -> new ScreeningResponse(screeningEntity.getScreeningId(), screeningEntity.getDate(), screeningEntity.getTime(), screeningEntity.getRoom(), screeningEntity.getMovie()))
                .collect(Collectors.toList());
        return screeningResponses;
    }
}
