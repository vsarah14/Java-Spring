package com.example.assignment1.business;

import com.example.assignment1.auth.AuthenticationRequest;
import com.example.assignment1.auth.AuthenticationResponse;
import com.example.assignment1.auth.RegisterRequest;
import com.example.assignment1.configuration.JwtService;
import com.example.assignment1.model.Role;
import com.example.assignment1.model.User;
import com.example.assignment1.persistance.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService service;
    private final AuthenticationManager manager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CASHIER)
                .build();
        repository.save(user);
        var token = service.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();

    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername()).orElseThrow();
        var token = service.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();

    }
}
