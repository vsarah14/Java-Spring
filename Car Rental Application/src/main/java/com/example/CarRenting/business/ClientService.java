package com.example.CarRenting.business;

import com.example.CarRenting.model.Client;
import com.example.CarRenting.persistance.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    //C - create operation
    public Client createClient(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepository.save(client);
    }

    //R - read operation
    public List<Client> readClient() {
        return clientRepository.findAll();
    }

    //U - update operation
    public Client updateClient(Client client, Integer clientId) {
        Client c = clientRepository.findById(clientId).get();

        if (Objects.nonNull(client.getUsername()) && !"".equalsIgnoreCase(client.getUsername())) {
            c.setUsername(client.getUsername());
        }

        if (Objects.nonNull(client.getPassword()) && !"".equalsIgnoreCase(client.getPassword())) {
            c.setPassword(passwordEncoder.encode(client.getPassword()));
        }

        if (Objects.nonNull(client.getEmail()) && !"".equalsIgnoreCase(client.getEmail())) {
            c.setEmail(client.getEmail());
        }

        if (Objects.nonNull(client.getFullName()) && !"".equalsIgnoreCase(client.getFullName())) {
            c.setFullName(client.getFullName());
        }


        return clientRepository.save(c);
    }

    //D - delete operation
    public String deleteClient(Integer clientId) {
        clientRepository.deleteById(clientId);
        return "Client deleted successfully.";
    }

    public Client findById(int id) {

        var val = clientRepository.findById(id);

        if (val.isPresent()) {
            Client client = val.get();
            return client;
        } else return null;
    }

}
