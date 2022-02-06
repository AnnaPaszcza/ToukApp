package com.example.toukapp.controllers;

import com.example.toukapp.dtos.ScreeningRequest;
import com.example.toukapp.dtos.ScreeningResponse;
import com.example.toukapp.services.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:8001")
public class ScreeningController {
    private final ScreeningService screeningService;

    @Autowired
    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @GetMapping("/api/screening")
    public ResponseEntity<List<ScreeningResponse>> getAll() {
        return new ResponseEntity<>(screeningService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/api/screening")
    public ResponseEntity<HttpStatus> addScreening(@RequestBody ScreeningRequest screeningRequest) {
        screeningService.addScreening(screeningRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/screening/{id}")
    public ResponseEntity<?> getScreening(@PathVariable int id){
        screeningService.getScreening(id);
        return new ResponseEntity<>(screeningService.getScreening(id), HttpStatus.OK);
    }

    @DeleteMapping("/api/screening/{id}")
    public ResponseEntity<HttpStatus> deleteScreening(@PathVariable int id){
        screeningService.deleteScreening(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/api/screening/{id}")
    public ResponseEntity<HttpStatus> updateScreening(@PathVariable int id, @RequestBody ScreeningRequest screeningRequest){
        screeningService.updateScreening(id, screeningRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
