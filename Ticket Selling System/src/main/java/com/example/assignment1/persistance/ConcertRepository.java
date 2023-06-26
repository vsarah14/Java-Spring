package com.example.assignment1.persistance;

import com.example.assignment1.model.Concert;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ConcertRepository extends CrudRepository<Concert, Integer> {

     Optional<Concert> findByName(String name);
     Optional<Concert> findById(Integer concertId);

}
