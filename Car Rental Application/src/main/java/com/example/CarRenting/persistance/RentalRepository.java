package com.example.CarRenting.persistance;

import com.example.CarRenting.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer>, CrudRepository<Rental, Integer> {
}
