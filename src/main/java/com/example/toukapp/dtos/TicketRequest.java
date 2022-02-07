package com.example.toukapp.dtos;

import com.example.toukapp.entity.Reservation;
import com.example.toukapp.entity.Screening;
import com.example.toukapp.entity.Seat;
import com.example.toukapp.entity.TicketType;
import lombok.Data;

@Data
public class TicketRequest {
    private int ticketId;
    private TicketType type;
    private Reservation reservation;
    private Screening screening;
    private Seat seat;
}
