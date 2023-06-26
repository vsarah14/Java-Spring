package com.example.assignment1.persistance;

import com.example.assignment1.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepositoryCSV extends JpaRepository<Ticket, Integer>, CrudRepository<Ticket, Integer> {

    Optional<Ticket> findById(Integer ticketId);
}
