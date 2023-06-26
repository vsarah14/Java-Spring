package com.example.CarRenting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private Integer carId;
    private String brand;
    private String model;
    private Integer kilometers;
    private Integer price;
    private String availability;
    private Integer administrator_id;
}
