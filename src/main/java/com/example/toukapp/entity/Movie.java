package com.example.toukapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MovieID")
    private int movieId;

    @Column(name = "Title")
    private String title;

    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
