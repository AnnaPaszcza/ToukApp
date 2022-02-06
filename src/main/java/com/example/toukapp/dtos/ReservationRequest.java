package com.example.toukapp.dtos;


import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class ReservationRequest {
    private int reservationId;
    private String name;
    private String surname;
    private Float totalPrice;
    private Date date;
    private Date expirationDate;
    private Time expirationTime;
}
