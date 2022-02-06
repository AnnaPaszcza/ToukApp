package com.example.toukapp.services;

import com.example.toukapp.dtos.SeatResponse;
import com.example.toukapp.dtos.SeatRequest;
import com.example.toukapp.dtos.SeatResponse;
import com.example.toukapp.entity.Seat;
import com.example.toukapp.entity.Seat;
import com.example.toukapp.repositories.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SeatServiceImplementation implements SeatService{
    private final SeatRepository seatRepository;

    @Autowired
    public SeatServiceImplementation(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<SeatResponse> getAll() {
        return StreamSupport.stream(seatRepository.findAll().spliterator(), false)
                .map(seatEntity -> new SeatResponse(seatEntity.getSeatId(), seatEntity.getRow(), seatEntity.getNumber(), seatEntity.getIsTaken(), seatEntity.getRoom()))
//                .map(seatEntity -> new SeatResponse(seatEntity.getSeatId(), seatEntity.getRow(), seatEntity.getNumber(), seatEntity.getIsTaken(), seatEntity.getRoom()))
                .collect(Collectors.toList());
    }

    @Override
    public SeatResponse getSeat(int seatId){
        List<SeatResponse> list = getAll();
        if(seatRepository.existsById(seatId)) {
            Optional<SeatResponse> foundSeat = list.stream().filter(seat -> seat.getSeatId() == seatId).findAny();
            return foundSeat.orElse(null);
        }
        return null;
    }

    @Override
    public void addSeat(SeatRequest seatRequest){
        Seat seatEntity = new Seat();
        seatEntity.setRow(seatRequest.getRow());
        seatEntity.setNumber(seatRequest.getNumber());
        seatEntity.setIsTaken(seatRequest.getIsTaken());
        seatEntity.setRoom(seatRequest.getRoom());
        seatRepository.save(seatEntity);
    }

    @Override
    public void deleteSeat(int seatId){
        if(seatRepository.existsById(seatId)){
            seatRepository.deleteById(seatId);
        }
    }

    @Override
    public void updateSeat(int seatId, SeatRequest seatRequest){
        if(seatRepository.existsById(seatId)){
            Seat seatEntity = new Seat();
            seatEntity.setRow(seatRequest.getRow());
            seatEntity.setNumber(seatRequest.getNumber());
            seatEntity.setIsTaken(seatRequest.getIsTaken());
            seatEntity.setRoom(seatRequest.getRoom());
            seatRepository.save(seatEntity);
        }
    }

//    @Override
    public List<SeatResponse> getAvailableInRoom(int roomId){
        return StreamSupport.stream(seatRepository.findAll().spliterator(), false)
                .filter(seat -> seat.getIsTaken().equals(false))
                .map(seatEntity -> new SeatResponse(seatEntity.getSeatId(), seatEntity.getRow(), seatEntity.getNumber(), seatEntity.getIsTaken(), seatEntity.getRoom()))
                .collect(Collectors.toList());
    }
}
