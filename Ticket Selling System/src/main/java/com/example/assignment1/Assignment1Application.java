package com.example.assignment1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootApplication
public class Assignment1Application {

    public static void main(String[] args) {

        SpringApplication.run(Assignment1Application.class, args);

    }
}
