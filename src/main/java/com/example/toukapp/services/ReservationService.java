package com.example.toukapp.services;

import com.example.toukapp.dtos.ReservationRequest;
import com.example.toukapp.dtos.ReservationResponse;

import java.util.List;

public interface ReservationService {
    List<ReservationResponse> getAll();
    ReservationResponse getReservation(int reservationId);
    void addReservation(ReservationRequest reservationRequest);
    void deleteReservation(int reservationId);
    void updateReservation(int reservationId, ReservationRequest reservationRequest);
    void addTicket(int ticketId, int price);
}
