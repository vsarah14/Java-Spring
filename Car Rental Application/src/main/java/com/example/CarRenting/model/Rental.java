package com.example.CarRenting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "_rental")
@SequenceGenerator(name = "rental_start", sequenceName = "rental_start", initialValue = 2)
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "rental_start")
    private Integer rentalId;
    private String date;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carId", referencedColumnName = "carId")
    private Car car;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client clientId;

    @JsonIgnore
    @OneToOne(mappedBy = "rental")
    private Contract contract;
}
