package com.example.toukapp.repositories;

import com.example.toukapp.entity.Seat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Integer> {
}