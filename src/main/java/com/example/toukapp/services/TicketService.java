package com.example.toukapp.services;

import com.example.toukapp.dtos.TicketRequest;
import com.example.toukapp.dtos.TicketResponse;

import java.util.List;

public interface TicketService {
    List<TicketResponse> getAll();
    TicketResponse getTicket(int ticketId);
    void addTicket(TicketRequest ticketRequest);
    void deleteTicket(int ticketId);
    void updateTicket(int ticketId, TicketRequest ticketRequest);
}
