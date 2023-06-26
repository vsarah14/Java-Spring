package com.example.assignment1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_ticket")
@SequenceGenerator(name = "ticket_start", sequenceName = "ticket_start", initialValue = 6)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ticket_start")
    private Integer id;

    private Integer concertId;
    private double price;
    private Integer personNumber;
}
