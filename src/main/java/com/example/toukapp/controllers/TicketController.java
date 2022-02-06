package com.example.toukapp.controllers;

import com.example.toukapp.dtos.TicketRequest;
import com.example.toukapp.dtos.TicketResponse;
import com.example.toukapp.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:8001")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/api/ticket")
    public ResponseEntity<List<TicketResponse>> getAll() {
        return new ResponseEntity<>(ticketService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/api/ticket")
    public ResponseEntity<HttpStatus> addTicket(@RequestBody TicketRequest ticketRequest) {
        ticketService.addTicket(ticketRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/ticket/{id}")
    public ResponseEntity<?> getTicket(@PathVariable int id){
        ticketService.getTicket(id);

        return new ResponseEntity<>(ticketService.getTicket(id), HttpStatus.OK);
    }

    @DeleteMapping("/api/ticket/{id}")
    public ResponseEntity<HttpStatus> deleteTicket(@PathVariable int id){
        ticketService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/ticket/{id}")
    public ResponseEntity<HttpStatus> updateTicket(@PathVariable int id, @RequestBody TicketRequest ticketRequest){
        ticketService.updateTicket(id, ticketRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}