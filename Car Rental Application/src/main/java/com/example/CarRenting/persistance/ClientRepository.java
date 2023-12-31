package com.example.CarRenting.persistance;

import com.example.CarRenting.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer>, CrudRepository<Client, Integer> {
    Optional<Client> findByUsername(String username);
}
