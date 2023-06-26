package com.example.assignment1.controller;

import com.example.assignment1.business.ConcertService;
import com.example.assignment1.model.Concert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity
public class ConcertController {

    @Autowired
    private ConcertService concertService;

    //C - create operation
    @PostMapping("/users/createConcert")
    @PreAuthorize("hasAuthority('ADMIN')")    //only the admin can access this method
    public Concert createConcert(@RequestBody Concert concert) {
        return concertService.createConcert(concert);
    }

    //R - read operation
    @GetMapping("/users/readConcert")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Concert> readConcert() {
        return concertService.readConcert();
    }

    //U - update operation
    @PutMapping("/users/updateConcert/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Concert updateUser(@RequestBody Concert concert, @PathVariable("id") Integer concertId) {
        return concertService.updateConcert(concert, concertId);
    }

    //D - delete operation
    @DeleteMapping("/users/deleteConcert/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteConcert(@PathVariable("id") Integer concertId) {
        concertService.deleteConcert(concertId);
        return "Concert deleted successfully.";
    }

}
