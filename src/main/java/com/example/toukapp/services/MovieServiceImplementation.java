package com.example.toukapp.services;

import com.example.toukapp.dtos.MovieRequest;
import com.example.toukapp.dtos.MovieResponse;
import com.example.toukapp.entity.Movie;
import com.example.toukapp.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieServiceImplementation implements MovieService{

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImplementation(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieResponse> getAll() {
        return StreamSupport.stream(movieRepository.findAll().spliterator(), false)
                .map(movieEntity -> new MovieResponse(movieEntity.getMovieId(), movieEntity.getTitle()))
                .collect(Collectors.toList());
    }

    @Override
    public MovieResponse getMovie(int movieId){
            List<MovieResponse> list = getAll();
            if(movieRepository.existsById(movieId)){
                return list.stream().filter(movie -> movie.getMovieId() == movieId).findAny().get();
            }
            return null;
    }

    @Override
    public void addMovie(MovieRequest movieRequest){
        Movie movieEntity = new Movie();
        movieEntity.setTitle(movieRequest.getName());
        movieRepository.save(movieEntity);
    }

    @Override
    public void deleteMovie(int movieId){
            if(movieRepository.existsById(movieId)){
                movieRepository.deleteById(movieId);
            }
    }

    @Override
    public void updateMovie(int movieId, MovieRequest movieRequest){
            if(movieRepository.existsById(movieId)){
                Movie movieEntity = new Movie();
                movieEntity.setTitle(movieRequest.getName());
                movieRepository.save(movieEntity);
            }
    }
}
