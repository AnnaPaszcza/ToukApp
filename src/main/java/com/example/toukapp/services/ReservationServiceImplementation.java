package com.example.toukapp.services;

import com.example.toukapp.dtos.ReservationRequest;
import com.example.toukapp.dtos.ReservationResponse;
import com.example.toukapp.entity.Reservation;
import com.example.toukapp.repositories.ReservationRepository;
import com.example.toukapp.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ReservationServiceImplementation implements ReservationService{
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImplementation(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<ReservationResponse> getAll() {
        return StreamSupport.stream(reservationRepository.findAll().spliterator(), false)
                .map(reservationEntity -> new ReservationResponse(reservationEntity.getReservationId(), reservationEntity.getName(), reservationEntity.getSurname(), reservationEntity.getTotalPrice(), reservationEntity.getDate(), reservationEntity.getExpirationDate(), reservationEntity.getExpirationTime()))
                .collect(Collectors.toList());
    }

    @Override
    public ReservationResponse getReservation(int reservationId){
        List<ReservationResponse> list = getAll();
        if(reservationRepository.existsById(reservationId)){
            return list.stream().filter(reservation -> reservation.getReservationId() == reservationId).findAny().get();
        }
        return null;
    }

    @Override
    public void addReservation(ReservationRequest reservationRequest){
        Reservation reservationEntity = new Reservation();
        int totalPrice = 0;
        reservationEntity.setName(reservationRequest.getName());
        reservationEntity.setSurname(reservationRequest.getSurname());
        reservationEntity.setTotalPrice(totalPrice);
        reservationEntity.setDate(reservationRequest.getDate());
        reservationEntity.setExpirationDate(reservationRequest.getExpirationDate());
        reservationEntity.setExpirationTime(reservationRequest.getExpirationTime());
        reservationRepository.save(reservationEntity);
    }

    @Override
    public void addTicket(int reservationId, int price) {
        ReservationResponse reservationResponse = this.getReservation(reservationId);
        ReservationRequest reservationRequest = new ReservationRequest();
        reservationResponse.setReservationId(reservationId);
        reservationResponse.setName(reservationResponse.getName());
        reservationResponse.setSurname(reservationResponse.getSurname());
        reservationResponse.setTotalPrice(reservationResponse.getTotalPrice() + price);
        reservationResponse.setDate(reservationResponse.getDate());
        reservationResponse.setExpirationTime(reservationResponse.getExpirationTime());
        reservationResponse.setExpirationDate(reservationResponse.getExpirationDate());
        this.updateReservation(reservationId, reservationRequest);
    }

    @Override
    public void deleteReservation(int reservationId){
        if(reservationRepository.existsById(reservationId)){
            reservationRepository.deleteById(reservationId);
        }
    }

    @Override
    public void updateReservation(int reservationId, ReservationRequest reservationRequest){
        if(reservationRepository.existsById(reservationId)){
            Reservation reservationEntity = new Reservation();
            reservationEntity.setName(reservationRequest.getName());
            reservationEntity.setSurname(reservationRequest.getSurname());
            reservationEntity.setTotalPrice(reservationRequest.getTotalPrice());
            reservationEntity.setDate(reservationRequest.getDate());
            reservationEntity.setExpirationDate(reservationRequest.getExpirationDate());
            reservationEntity.setExpirationTime(reservationRequest.getExpirationTime());
            reservationRepository.save(reservationEntity);
        }
    }
}
