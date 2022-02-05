package com.example.toukapp.services;

import com.example.toukapp.dtos.TicketTypeRequest;
import com.example.toukapp.dtos.TicketTypeResponse;

import java.util.List;

public interface TicketTypeService {
    List<TicketTypeResponse> getAll();
    TicketTypeResponse getTicketType(int ticketTypeId);
    void addTicketType(TicketTypeRequest ticketTypeRequest);
    void deleteTicketType(int ticketTypeId);
    void updateTicketType(int ticketTypeId, TicketTypeRequest ticketTypeRequest);
}
