package com.example.CarRenting.persistance;

import com.example.CarRenting.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer>, CrudRepository<Administrator, Integer> {
    Optional<Administrator> findByUsername(String username);
}
