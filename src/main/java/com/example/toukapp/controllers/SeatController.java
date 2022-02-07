package com.example.toukapp.controllers;

import com.example.toukapp.dtos.SeatRequest;
import com.example.toukapp.dtos.SeatResponse;
import com.example.toukapp.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:8001")
public class SeatController {
    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/api/seat")
    public ResponseEntity<List<SeatResponse>> getAll() {
        return new ResponseEntity<>(seatService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/api/seat")
    public ResponseEntity<HttpStatus> addSeat(@RequestBody SeatRequest seatRequest) {
        seatService.addSeat(seatRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/seat/{id}")
    public ResponseEntity<?> getSeat(@PathVariable int id){
        seatService.getSeat(id);
        return new ResponseEntity<>(seatService.getSeat(id), HttpStatus.OK);
    }

    @DeleteMapping("/api/seat/{id}")
    public ResponseEntity<HttpStatus> deleteSeat(@PathVariable int id){
        seatService.deleteSeat(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/seat/{id}")
    public ResponseEntity<HttpStatus> updateSeat(@PathVariable int id, @RequestBody SeatRequest seatRequest){
        seatService.updateSeat(id, seatRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}