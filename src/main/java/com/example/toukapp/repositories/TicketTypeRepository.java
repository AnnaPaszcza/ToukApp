package com.example.toukapp.repositories;

import com.example.toukapp.entity.TicketType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketTypeRepository extends CrudRepository<TicketType, Integer> {
}
