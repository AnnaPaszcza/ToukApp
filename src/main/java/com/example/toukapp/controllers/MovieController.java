package com.example.toukapp.controllers;

import com.example.toukapp.dtos.MovieRequest;
import com.example.toukapp.dtos.MovieResponse;
import com.example.toukapp.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:8001")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/api/movie")
    public ResponseEntity<List<MovieResponse>> getAll() {
        return new ResponseEntity<>(movieService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/api/movie")
    public ResponseEntity addMovie(@RequestBody MovieRequest movieRequest) {
        movieService.addMovie(movieRequest);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/api/movie/{id}")
    public ResponseEntity getMovie(@PathVariable int id){
        movieService.getMovie(id);

        return new ResponseEntity(movieService.getMovie(id), HttpStatus.OK);
    }

    @DeleteMapping("/api/movie/{id}")
    public ResponseEntity deleteMovie(@PathVariable int id){
        movieService.deleteMovie(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/api/movie/{id}")
    public ResponseEntity updateMovie(@PathVariable int id, @RequestBody MovieRequest movieRequest){
        movieService.updateMovie(id, movieRequest);
        return new ResponseEntity(HttpStatus.OK);
    }
}
