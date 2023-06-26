package com.example.CarRenting.controller;

import com.example.CarRenting.auth.AuthenticationRequest;
import com.example.CarRenting.auth.AuthenticationResponse;
import com.example.CarRenting.auth.RegisterRequest;
import com.example.CarRenting.business.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@EnableMethodSecurity
public class AuthenticationController {

    private final AuthenticationService service;

    //register administrator
    @PostMapping("/api/v1/auth/registerAdministrator")
    public ResponseEntity<AuthenticationResponse> registerA(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerAdministrator(request));
    }

    //register employee
    @PostMapping("/api/v1/auth/registerEmployee")
    public ResponseEntity<AuthenticationResponse> registerE(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerEmployee(request));
    }

    //register client
    @PostMapping("/api/v1/auth/registerClient")
    public ResponseEntity<AuthenticationResponse> registerC(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerClient(request));
    }

    //login administrator
    @PostMapping("/loginAdministrator")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public ResponseEntity<AuthenticationResponse> loginA(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.loginAdministrator(request));
    }

    //login employee
    @PostMapping("/loginEmployee")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public ResponseEntity<AuthenticationResponse> loginE(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.loginEmployee(request));
    }

    //login client
    @PostMapping("/loginClient")
    @PreAuthorize("hasAuthority('CLIENT')")
    public ResponseEntity<AuthenticationResponse> loginC(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.loginClient(request));
    }

}
