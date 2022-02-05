package com.example.toukapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Data
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ReservationID")
    private int reservationId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "TotalPrice")
    private int totalPrice;

    @Column(name = "ReservationDate")
    private Date date;

    @Column(name = "ExpirationDate")
    private Date expirationDate;

    @Column(name = "ExpirationTime")
    private Time expirationTime;

    public int getReservationId() {
        return reservationId;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Time getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Time expirationTime) {
        this.expirationTime = expirationTime;
    }
}
