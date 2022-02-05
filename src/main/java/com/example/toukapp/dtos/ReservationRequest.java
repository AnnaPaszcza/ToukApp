package com.example.toukapp.dtos;


import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class ReservationRequest {
    private int reservationId;
    private String name;
    private String surname;
    private int totalPrice;
    private Date date;
    private Date expirationDate;
    private Time expirationTime;
}
