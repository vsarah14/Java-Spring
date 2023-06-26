package com.example.assignment2.controller;

import com.example.assignment2.auth.AuthenticationRequest;
import com.example.assignment2.auth.AuthenticationResponse;
import com.example.assignment2.auth.RegisterRequest;
import com.example.assignment2.model.Student;
import com.example.assignment2.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@EnableMethodSecurity
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/api/v1/auth/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.registerTeacher(request));
    }

    @PostMapping("/createStudent")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<AuthenticationResponse> create(
            @RequestBody Student request
    ) {
        return ResponseEntity.ok(service.createStudent(request));
    }

    @PostMapping("/api/v1/auth/loginTeacher")
    public ResponseEntity<AuthenticationResponse> loginT(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.loginTeacher(request));
    }

    @PostMapping("/loginStudent")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<AuthenticationResponse> loginS(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.loginStudent(request));
    }

}
