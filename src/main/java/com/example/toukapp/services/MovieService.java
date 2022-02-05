package com.example.toukapp.services;

import com.example.toukapp.dtos.MovieRequest;
import com.example.toukapp.dtos.MovieResponse;

import java.util.List;

public interface MovieService {
    List<MovieResponse> getAll();
    MovieResponse getMovie(int movieId);
    void addMovie(MovieRequest movieRequest);
    void deleteMovie(int movieId);
    void updateMovie(int movieId, MovieRequest movieRequest);
    //user selects the day and the time when they would like to see the movie
    //system lists all movie available in the given time interval- title and screening time
    //sorted by title and screening time
    //the user choses screening
    //system gives info about room and available seats
    //user choses seats and gives the name and surname
    //system gives back the total amount to pay and expiration time
}
