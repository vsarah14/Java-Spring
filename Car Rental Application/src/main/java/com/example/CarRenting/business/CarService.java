package com.example.CarRenting.business;

import com.example.CarRenting.model.Administrator;
import com.example.CarRenting.model.Car;
import com.example.CarRenting.model.dto.CarDTO;
import com.example.CarRenting.model.iter.car.CarContainer;
import com.example.CarRenting.persistance.CarRepository;
import com.example.CarRenting.model.iter.Iterator;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CarService implements Observer {
    @Autowired
    private CarRepository carRepository;
    private AdministratorService administratorService;

    public Car toEntity(CarDTO car) {
        Administrator administrator = administratorService.findById(car.getAdministrator_id());
        return Car.builder()
                .carId(car.getCarId())
                .brand(car.getBrand())
                .model(car.getModel())
                .price(car.getPrice())
                .availability(car.getAvailability())
                .kilometers(car.getKilometers())
                .administratorId(administrator)
                .build();
    }

    public CarDTO fromEntity(Car car) {
        return CarDTO.builder()
                .carId(car.getCarId())
                .brand(car.getBrand())
                .model(car.getModel())
                .price(car.getPrice())
                .availability(car.getAvailability())
                .kilometers(car.getKilometers())
                .administrator_id(car.getAdministratorId().getAdministratorId())
                .build();
    }

    //C - create operation
    public Car createCar(CarDTO car) {
        Car c = toEntity(car);
        return carRepository.save(c);
    }

    //R - read operation
    public List<Car> readCar() {
        return carRepository.findAll();
    }

    //U - update operation
    public Car updateCar(CarDTO car, Integer carId) {
        Car c = carRepository.findById(carId).get();
        CarDTO carDTO = fromEntity(c);

        if (Objects.nonNull(car.getModel()) && !"".equalsIgnoreCase(car.getModel())) {
            carDTO.setModel(car.getModel());
        }

        if (Objects.nonNull(car.getBrand()) && !"".equalsIgnoreCase(car.getBrand())) {
            carDTO.setBrand(car.getBrand());
        }

        if (Objects.nonNull(car.getPrice()) && !"".equalsIgnoreCase(car.getPrice().toString())) {
            carDTO.setPrice(car.getPrice());
        }

        if (Objects.nonNull(car.getAvailability()) && !"".equalsIgnoreCase(car.getAvailability())) {
            carDTO.setAvailability(car.getAvailability());
        }

        if (Objects.nonNull(car.getAvailability()) && !"".equalsIgnoreCase(car.getAvailability())) {
            carDTO.setAvailability(car.getAvailability());
        }

        if (Objects.nonNull(car.getKilometers()) && !"".equalsIgnoreCase(car.getKilometers().toString())) {
            carDTO.setKilometers(car.getKilometers());
        }

        if (Objects.nonNull(car.getAdministrator_id()) && !"".equalsIgnoreCase(car.getAdministrator_id().toString())) {
            carDTO.setAdministrator_id(car.getAdministrator_id());
        }

        return carRepository.save(toEntity(carDTO));
    }

    //D - delete operation
    public void deleteCar(Integer carId) {
        carRepository.deleteById(carId);
    }

    //read car for administrator
    public List<Car> readCarForAdmin(Integer carId) {
        Administrator administrator = administratorService.findById(carId);
        List<Car> list = carRepository.findAll();
        List<Car> finalList = new ArrayList<>();
        for (Car car : list)
            if (car.getAdministratorId().equals(administrator)) {
                finalList.add(car);
            }
        return finalList;
    }

    public Car findById(int id) throws NoSuchElementException {
        CarContainer carContainer = new CarContainer(readCar().toArray((Car[]::new)));
        Iterator<Car> iterator = carContainer.getIterator();
        while(iterator.hasNext()){
            Car car = iterator.next();
            if(car.getCarId() == id){
                return car;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void update(Observable o, Object arg){
        CarDTO carInfo = (CarDTO) arg;
        updateCar(carInfo, carInfo.getCarId());
    }
}
