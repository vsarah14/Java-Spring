package com.example.CarRenting.controller;

import com.example.CarRenting.business.CarService;
import com.example.CarRenting.model.Car;
import com.example.CarRenting.model.CarUpdate;
import com.example.CarRenting.model.dto.CarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity
public class CarController {

    @Autowired
    private CarService carService;

    //C - create operation
    @PostMapping("/car/createCar")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public Car createAssignment(@RequestBody CarDTO car) {
        return carService.createCar(car);
    }

    //R - read operation
    @GetMapping("/car/readCar")
    @PreAuthorize("hasAuthority('CLIENT')")
    public List<Car> readAssignment() {
        return carService.readCar();
    }

    //U - update operation
    @PutMapping("/car/updateCar/{carId}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public Car updateCar(@RequestBody CarUpdate car, @PathVariable("carId") Integer carId) {
        car.addObserver(carService);
        car.setCarId(carId);
        return carService.findById(carId);
    }

    //D - delete operation
    @DeleteMapping("/car/deleteCar/{carId}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String deleteCar(@PathVariable("carId") Integer carId) {
        carService.deleteCar(carId);
        return "Car deleted successfully.";
    }

    //read cars for an administrator
    @GetMapping("/car/readCarAdmin/{adminId}")
    @PreAuthorize("hasAuthority('CLIENT')")
    public List<Car> readCarForAdmin(@PathVariable("adminId") Integer adminId) {
        return carService.readCarForAdmin(adminId);
    }
}
