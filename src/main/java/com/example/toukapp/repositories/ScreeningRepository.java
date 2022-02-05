package com.example.toukapp.repositories;

import com.example.toukapp.entity.Screening;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningRepository extends CrudRepository<Screening, Integer> {
}
