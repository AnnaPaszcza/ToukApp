package com.example.toukapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketTypeResponse {
    private int typeId;
    private String name;
    private Float price;
}
