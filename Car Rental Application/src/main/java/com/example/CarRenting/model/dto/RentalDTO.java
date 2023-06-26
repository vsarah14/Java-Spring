package com.example.CarRenting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {
    private Integer rentalId;
    private String date;
    private Integer car_id;
    private Integer client_id;
}
