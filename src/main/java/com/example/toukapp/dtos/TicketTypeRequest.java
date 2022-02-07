package com.example.toukapp.dtos;

import lombok.Data;

@Data
public class TicketTypeRequest {
    private int typeId;
    private String name;
    private Float price;
}
