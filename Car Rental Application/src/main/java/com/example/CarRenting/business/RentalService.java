package com.example.CarRenting.business;

import com.example.CarRenting.model.Car;
import com.example.CarRenting.model.Client;
import com.example.CarRenting.model.Rental;
import com.example.CarRenting.model.dto.RentalDTO;
import com.example.CarRenting.persistance.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;
    private CarService carService;
    private ClientService clientService;

    public Rental toEntity(RentalDTO rental){
        Car car = carService.findById(rental.getCar_id());
        Client client = clientService.findById(rental.getClient_id());
        return Rental.builder()
                .rentalId(rental.getRentalId())
                .clientId(client)
                .car(car)
                .date(rental.getDate())
                .build();
    }

    public RentalDTO fromEntity(Rental rental){
        return RentalDTO.builder()
                .rentalId(rental.getRentalId())
                .date(rental.getDate())
                .client_id(rental.getClientId().getClientId())
                .car_id(rental.getCar().getCarId())
                .build();
    }

    //C - create a rental
    public Rental createRental(RentalDTO rental) {
        Rental r = toEntity(rental);
        return rentalRepository.save(r);
    }

    public Rental findById(int id) {

        var val = rentalRepository.findById(id);

        if (val.isPresent()) {
            Rental rental = val.get();
            return rental;
        } else return null;
    }
}