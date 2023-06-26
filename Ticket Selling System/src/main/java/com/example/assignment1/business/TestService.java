package com.example.assignment1.business;

import com.example.assignment1.model.*;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@RequiredArgsConstructor
public class TestService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Test
    public void Test() throws ParameterResolutionException{

        //tests for password encryption
        User user1 = new User(1,"admin", "admin", Role.ADMIN);
        user1.setPassword(passwordEncoder.encode(user1.getPassword()));
        System.out.println(user1.getPassword());
        assertTrue(passwordEncoder.matches("admin", user1.getPassword()));

        //test for ticket validation
        Band band1 = new Band(1,"RATB", "rock",null);
        Concert concert = new Concert(1, "Festivalul Ploii", 0, "13 aprilie", List.of(band1));
        Ticket ticket = new Ticket(1, 1,100,3);
        int noTickets = concert.getTickets() - ticket.getPersonNumber();
        assertTrue(noTickets<=0);

    }
}

