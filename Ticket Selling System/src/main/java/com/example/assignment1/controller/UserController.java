package com.example.assignment1.controller;

import com.example.assignment1.business.UserService;
import com.example.assignment1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@EnableMethodSecurity
public class UserController {

    @Autowired
    private UserService userService;

    //C - create operation
    @PostMapping("/users/createUser")
    @PreAuthorize("hasAuthority('ADMIN')")    //only the admin can access this method
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    //R - read operation
    @GetMapping("/users/readUser")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> readUser() {
        return userService.readUser();
    }

    //U - update operation
    @PutMapping("/users/updateUser/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User updateUser(@RequestBody User user, @PathVariable("id") Integer userId) {
        return userService.updateUser(user, userId);
    }

    //D - delete operation
    @DeleteMapping("/users/deleteUser/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteUser(@PathVariable("id") Integer userId) {
        userService.deleteUser(userId);
        return "User deleted successfully.";
    }

}
