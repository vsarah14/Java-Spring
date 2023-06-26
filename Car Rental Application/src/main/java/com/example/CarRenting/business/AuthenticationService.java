package com.example.CarRenting.business;

import com.example.CarRenting.auth.AuthenticationRequest;
import com.example.CarRenting.auth.AuthenticationResponse;
import com.example.CarRenting.auth.RegisterRequest;
import com.example.CarRenting.business.observer.Subject;
import com.example.CarRenting.configuration.JwtService;
import com.example.CarRenting.model.Administrator;
import com.example.CarRenting.model.Client;
import com.example.CarRenting.model.Employee;
import com.example.CarRenting.model.Role;
import com.example.CarRenting.persistance.AdministratorRepository;
import com.example.CarRenting.persistance.ClientRepository;
import com.example.CarRenting.persistance.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Component
public class AuthenticationService extends Subject {

    private final ClientRepository clientRepository;
    private final AdministratorRepository administratorRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService service;
    private final AuthenticationManager manager;

    //for administrator - register and login
    public AuthenticationResponse registerAdministrator(RegisterRequest request) {
        var administrator = Administrator.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMINISTRATOR)
                .email(request.getEmail())
                .fullName(request.getFullName())
                .build();
        administratorRepository.save(administrator);
        var token = service.generateToken(administrator);
        return AuthenticationResponse.builder().token(token).build();
    }

    public AuthenticationResponse loginAdministrator(AuthenticationRequest request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = administratorRepository.findByUsername(request.getUsername()).orElseThrow();
        var token = service.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }

    //for employee - register and login
    public AuthenticationResponse registerEmployee(RegisterRequest request) {
        var employee = Employee.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.EMPLOYEE)
                .email(request.getEmail())
                .fullName(request.getFullName())
                .build();
        employeeRepository.save(employee);
        var token = service.generateToken(employee);
        return AuthenticationResponse.builder().token(token).build();
    }

    public AuthenticationResponse loginEmployee(AuthenticationRequest request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = employeeRepository.findByUsername(request.getUsername()).orElseThrow();
        var token = service.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }

    //for client - register and login
    public AuthenticationResponse registerClient(RegisterRequest request) {
        var client = Client.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CLIENT)
                .email(request.getEmail())
                .fullName(request.getFullName())
                .build();
        try {
            notifyObservers(1, client.getUsername());
        } catch (IOException e) {
             System.out.println("Error");
        }
        clientRepository.save(client);
        var token = service.generateToken(client);
        return AuthenticationResponse.builder().token(token).build();
    }


    public AuthenticationResponse loginClient(AuthenticationRequest request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = clientRepository.findByUsername(request.getUsername()).orElseThrow();
        var token = service.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }
}
