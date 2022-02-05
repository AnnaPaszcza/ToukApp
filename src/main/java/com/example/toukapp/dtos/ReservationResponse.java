package com.example.toukapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponse {
    private int reservationId;
    private String name;
    private String surname;
    private int totalPrice;
    private Date date;
    private Date expirationDate;
    private Time expirationTime;
}
