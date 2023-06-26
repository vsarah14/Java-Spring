package com.example.CarRenting.controller;

import com.example.CarRenting.business.RentalService;
import com.example.CarRenting.model.Car;
import com.example.CarRenting.model.Rental;
import com.example.CarRenting.model.dto.CarDTO;
import com.example.CarRenting.model.dto.RentalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableMethodSecurity
public class RentalController {

    @Autowired
    private RentalService rentalService;

    //C - create rental
    @PostMapping("/rental/createRental")
    @PreAuthorize("hasAuthority('CLIENT')")
    public Rental createRental(@RequestBody RentalDTO rental) {
        return rentalService.createRental(rental);
    }

}
