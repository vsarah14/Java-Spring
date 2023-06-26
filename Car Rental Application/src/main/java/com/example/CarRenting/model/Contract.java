package com.example.CarRenting.model;

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
@Table(name = "_contract")
@SequenceGenerator(name = "contract_start", sequenceName = "contract_start", initialValue = 2)
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "contract_start")
    private Integer contractId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rentalId", referencedColumnName = "rentalId")
    private Rental rental;
    private String signature;


}
