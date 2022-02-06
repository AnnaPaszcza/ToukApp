package com.example.toukapp.controllers;

import com.example.toukapp.dtos.MakeReservationRequest;
import com.example.toukapp.dtos.ReservationRequest;
import com.example.toukapp.dtos.ReservationResponse;
import com.example.toukapp.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:8001")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/api/reservation")
    public ResponseEntity<List<ReservationResponse>> getAll() {
        return new ResponseEntity<>(reservationService.getAll(), HttpStatus.OK);
    }

//    @PostMapping("/api/reservation")
//    public ResponseEntity<HttpStatus> addReservation(@RequestBody ReservationRequest reservationRequest) {
//        reservationService.addReservation(reservationRequest);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping("/api/reservation/{id}")
    public ResponseEntity<?> getReservation(@PathVariable int id){
        reservationService.getReservation(id);
        return new ResponseEntity<>(reservationService.getReservation(id), HttpStatus.OK);
    }

    @DeleteMapping("/api/reservation/{id}")
    public ResponseEntity<HttpStatus> deleteReservation(@PathVariable int id){
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/reservation/{id}")
    public ResponseEntity<HttpStatus> updateReservation(@PathVariable int id, @RequestBody ReservationRequest reservationRequest){
        reservationService.updateReservation(id, reservationRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/reservation")
    public ResponseEntity<Float> makeReservation(@RequestBody MakeReservationRequest makeReservationRequest) {
        Float totalPrice = reservationService.makeReservation(makeReservationRequest);
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }
}

