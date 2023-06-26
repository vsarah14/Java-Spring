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
@Table(name = "_car")
@SequenceGenerator(name = "car_start", sequenceName = "car_start", initialValue = 3)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "car_start")
    private Integer carId;
    private String brand;
    private String model;
    private Integer kilometers;
    private Integer price;
    private String availability;
    @JsonIgnore
    @OneToOne(mappedBy = "car")
    private Rental rent;
    @ManyToOne
    @JoinColumn(name = "administrator_id")
    private Administrator administratorId;

}
