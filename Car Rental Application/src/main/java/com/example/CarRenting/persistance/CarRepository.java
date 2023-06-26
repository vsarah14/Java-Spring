package com.example.CarRenting.persistance;

import com.example.CarRenting.model.Car;

import com.example.CarRenting.model.dto.CarDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
