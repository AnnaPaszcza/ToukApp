package com.example.toukapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SeatID")
    protected int seatId;

    @Column(name = "SeatRow")
    private int row;

    @Column(name = "SeatNumber")
    private int number;

    @Column(name = "IsTaken")
    private boolean isTaken;

    @ManyToOne
    @JoinColumn(name = "RoomID")
    private Room room;

    public int getSeatId() {
        return seatId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Boolean getIsTaken() {
        return isTaken;
    }

    public void setIsTaken(Boolean isTaken) {
        this.isTaken = isTaken;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
