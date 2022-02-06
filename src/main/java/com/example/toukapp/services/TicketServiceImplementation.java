package com.example.toukapp.services;

import com.example.toukapp.dtos.TicketRequest;
import com.example.toukapp.dtos.TicketResponse;
import com.example.toukapp.entity.Ticket;
import com.example.toukapp.repositories.TicketRepository;
import com.example.toukapp.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class TicketServiceImplementation implements TicketService{
    private final TicketRepository ticketRepository;
//    private final ReservationRepository reservationRepository;

    @Autowired
    public TicketServiceImplementation(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<TicketResponse> getAll() {
        return StreamSupport.stream(ticketRepository.findAll().spliterator(), false)
                .map(ticketEntity -> new TicketResponse(ticketEntity.getTicketId(), ticketEntity.getTicketType(), ticketEntity.getReservation(), ticketEntity.getScreening(), ticketEntity.getSeat()))
                .collect(Collectors.toList());
    }

    @Override
    public TicketResponse getTicket(int ticketId){
        List<TicketResponse> list = getAll();
        if(ticketRepository.existsById(ticketId)){
            return list.stream().filter(ticket -> ticket.getTicketId() == ticketId).findAny().get();
        }
        return null;
    }

    @Override
    public void addTicket(TicketRequest ticketRequest){
        if (ticketRequest.getSeat() != null) {
            Ticket ticketEntity = new Ticket();
            ticketEntity.setTicketType(ticketRequest.getType());
            ticketEntity.setReservation(ticketRequest.getReservation());
            ticketEntity.setScreening(ticketRequest.getScreening());
            ticketEntity.setSeat(ticketRequest.getSeat());
            ticketRepository.save(ticketEntity);
        }
    }

    @Override
    public void deleteTicket(int ticketId){
        if(ticketRepository.existsById(ticketId)){
            ticketRepository.deleteById(ticketId);
        }
    }

    @Override
    public void updateTicket(int ticketId, TicketRequest ticketRequest){
        if(ticketRepository.existsById(ticketId)){
            Ticket ticketEntity = new Ticket();
            ticketEntity.setTicketType(ticketRequest.getType());
            ticketEntity.setReservation(ticketRequest.getReservation());
            ticketEntity.setScreening(ticketRequest.getScreening());
            ticketEntity.setSeat(ticketRequest.getSeat());
            ticketRepository.save(ticketEntity);
        }
    }
}
