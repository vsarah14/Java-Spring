package com.example.CarRenting.controller;

import com.example.CarRenting.business.AdministratorService;
import com.example.CarRenting.model.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity
public class AdministratorController {

    @Autowired
    private final AdministratorService administratorService;

    public AdministratorController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    //C - create operation
    @PostMapping("/administrator/createAdministrator")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public Administrator createAdministrator(@RequestBody Administrator administrator) {
        return administratorService.createAdministrator(administrator);
    }

    //R - read operation
    @GetMapping("/administrator/readAdministrator")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public List<Administrator> readAdministrator() {
        return administratorService.readAdministrator();
    }

    //U - update operation
    @PutMapping("/administrator/updateAdministrator/{administratorId}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public Administrator updateAdministrator(@RequestBody Administrator administrator, @PathVariable("administratorId") Integer administratorId) {
        return administratorService.updateAdministrator(administrator, administratorId);
    }

    //D - delete operation
    @DeleteMapping("/administrator/deleteAdministrator/{administratorId}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public String deleteAdministrator(@PathVariable("administratorId") Integer administratorId) {
        return administratorService.deleteAdministrator(administratorId);
    }
}
