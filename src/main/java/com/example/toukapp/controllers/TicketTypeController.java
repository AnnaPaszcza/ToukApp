package com.example.toukapp.controllers;

import com.example.toukapp.dtos.TicketTypeRequest;
import com.example.toukapp.dtos.TicketTypeResponse;
import com.example.toukapp.services.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:8001")
public class TicketTypeController {
    private final TicketTypeService ticketTypeService;

    @Autowired
    public TicketTypeController(TicketTypeService ticketTypeService) {
        this.ticketTypeService = ticketTypeService;
    }

    @GetMapping("/api/ticketType")
    public ResponseEntity<List<TicketTypeResponse>> getAll() {
        return new ResponseEntity<>(ticketTypeService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/api/ticketType")
    public ResponseEntity<HttpStatus> addTicketType(@RequestBody TicketTypeRequest ticketTypeRequest) {
        ticketTypeService.addTicketType(ticketTypeRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/ticketType/{id}")
    public ResponseEntity<?> getTicketType(@PathVariable int id){
        ticketTypeService.getTicketType(id);

        return new ResponseEntity<>(ticketTypeService.getTicketType(id), HttpStatus.OK);
    }

    @DeleteMapping("/api/ticketType/{id}")
    public ResponseEntity<HttpStatus> deleteTicketType(@PathVariable int id){
        ticketTypeService.deleteTicketType(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/ticketType/{id}")
    public ResponseEntity<HttpStatus> updateTicketType(@PathVariable int id, @RequestBody TicketTypeRequest ticketTypeRequest){
        ticketTypeService.updateTicketType(id, ticketTypeRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
