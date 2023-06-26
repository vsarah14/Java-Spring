package com.example.CarRenting.model;

import com.example.CarRenting.model.dto.CarDTO;

import java.util.Observable;

public class CarUpdate extends Observable {

    private Integer carId;
    private String brand;
    private String model;
    private Integer kilometers;
    private Integer price;
    private String availability;
    private Integer administrator_id;

    public CarDTO buildCar() {
        return CarDTO.builder()
                .carId(carId)
                .brand(brand)
                .model(model)
                .kilometers(kilometers)
                .price(price)
                .availability(availability)
                .administrator_id(administrator_id)
                .build();
    }

    public void setCarId(Integer id) {
        this.carId = id;
        fireChange();
    }

    public void fireChange() {
        setChanged();
        System.out.println("Notified");
        notifyObservers(buildCar());
    }
}
