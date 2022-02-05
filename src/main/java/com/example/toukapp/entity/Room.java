package com.example.toukapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RoomID")
    private int roomId;

    @Column(name = "Name")
    private String name;

    public int getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
