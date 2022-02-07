package com.example.toukapp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tickettypes")
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TypeID")
    private int typeId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Price")
    private Float price;

    public int getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
