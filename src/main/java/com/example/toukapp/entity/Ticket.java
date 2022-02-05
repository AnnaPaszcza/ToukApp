package com.example.toukapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Data
@Table(name = "tickets")
public class Ticket {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "TicketID")
        private int ticketId;

        @ManyToOne
        @JoinColumn(name = "TypeID")
        private TicketType type;

        @ManyToOne
        @JoinColumn(name = "ReservationID")
        private Reservation reservation;

        @ManyToOne
        @JoinColumn(name = "ScreeningID")
        private Screening screening;

        @OneToOne
        @JoinColumn(name = "SeatID")
        private Seat seat;

    public int getTicketId() {
        return ticketId;
    }

    public TicketType getTicketType() {
        return type;
    }

    public void setTicketType(TicketType type) {
        this.type = type;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
