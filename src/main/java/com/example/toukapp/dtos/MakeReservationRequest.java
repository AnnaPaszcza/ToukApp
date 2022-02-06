package com.example.toukapp.dtos;

import lombok.Data;
//import springfox.documentation.schema.Entry;

import java.util.List;
import java.util.Map;

@Data
public class MakeReservationRequest {
    private String name;
    private String surname;
    private int screeningId;
    private List<Map.Entry<Integer,Integer>> chosenSeatIds;
}
