package com.example.toukapp.services;

import com.example.toukapp.dtos.TicketTypeRequest;
import com.example.toukapp.dtos.TicketTypeResponse;
import com.example.toukapp.entity.TicketType;
import com.example.toukapp.repositories.TicketTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TicketTypeServiceImplementation implements TicketTypeService{
    private final TicketTypeRepository ticketTypeRepository;

    @Autowired
    public TicketTypeServiceImplementation(TicketTypeRepository ticketTypeRepository) {
        this.ticketTypeRepository = ticketTypeRepository;
    }

    @Override
    public List<TicketTypeResponse> getAll() {
        return StreamSupport.stream(ticketTypeRepository.findAll().spliterator(), false)
                .map(ticketTypeEntity -> new TicketTypeResponse(ticketTypeEntity.getTypeId(), ticketTypeEntity.getName(), ticketTypeEntity.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public TicketTypeResponse getTicketType(int ticketTypeId) {
        List<TicketTypeResponse> list = getAll();
        if(ticketTypeRepository.existsById(ticketTypeId)) {
            Optional<TicketTypeResponse> foundTicketType = list.stream().filter(ticketType -> ticketType.getTypeId() == ticketTypeId).findAny();
            return foundTicketType.orElse(null);
        }
        return null;
    }

    @Override
    public void addTicketType(TicketTypeRequest ticketTypeRequest){
        TicketType ticketTypeEntity = new TicketType();
        ticketTypeEntity.setName(ticketTypeRequest.getName());
        ticketTypeEntity.setPrice(ticketTypeRequest.getPrice());
        ticketTypeRepository.save(ticketTypeEntity);
    }

    @Override
    public void deleteTicketType(int ticketTypeId){
        if(ticketTypeRepository.existsById(ticketTypeId)){
            ticketTypeRepository.deleteById(ticketTypeId);
        }
    }

    @Override
    public void updateTicketType(int ticketTypeId, TicketTypeRequest ticketTypeRequest){
        if(ticketTypeRepository.existsById(ticketTypeId)){
            TicketType ticketTypeEntity = new TicketType();
            ticketTypeEntity.setName(ticketTypeRequest.getName());
            ticketTypeEntity.setPrice(ticketTypeRequest.getPrice());
            ticketTypeRepository.save(ticketTypeEntity);
        }
    }
}
