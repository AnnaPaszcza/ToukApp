package com.example.toukapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;


@Entity
@Data
@Table(name = "screenings")
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ScreeningID")
    private int screeningId;

    @Column(name = "`Date`")
    private Date date;

    @Column(name = "ScreeningTime")
    private Time time;

    @ManyToOne
    @JoinColumn(name = "MovieID")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "RoomID")
    private Room room;

    public int getScreeningId() {
        return screeningId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
