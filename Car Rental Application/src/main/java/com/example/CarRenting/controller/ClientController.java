package com.example.CarRenting.controller;

import com.example.CarRenting.business.ClientService;
import com.example.CarRenting.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity
public class ClientController {
    @Autowired
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //C - create operation
    @PostMapping("/client/createClient")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    //R - read operation
    @GetMapping("/client/readClient")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public List<Client> readStudent() {
        return clientService.readClient();
    }

    //U - update operation
    @PutMapping("/client/updateClient/{clientId}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public Client updateClient(@RequestBody Client client, @PathVariable("clientId") Integer clientId) {
        return clientService.updateClient(client, clientId);
    }

    //D - delete operation
    @DeleteMapping("/client/deleteClient/{clientId}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public String deleteClient(@PathVariable("clientId") Integer teacherId) {
        return clientService.deleteClient(teacherId);
    }
}
