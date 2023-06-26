package com.example.assignment1.business;

import com.example.assignment1.model.User;
import com.example.assignment1.persistance.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //C - create operation
    public User createUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    //R - read operation
    public List<User> readUser(){
        return userRepository.findAll();
    }

    //U - update operation
    public User updateUser(User user, Integer userId) {
        User u = userRepository.findById(userId).get();

        if(Objects.nonNull(user.getUsername()) && !"".equalsIgnoreCase( user.getUsername())){
            u.setUsername(user.getUsername());
        }

        if(Objects.nonNull(user.getPassword()) && !"".equalsIgnoreCase( user.getPassword())){
            u.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if(Objects.nonNull(user.getRole()) && !"".equalsIgnoreCase( user.getRole().name())){
            u.setRole(user.getRole());
        }

        return userRepository.save(u);
    }

    //D - delete operation
    public void deleteUser(Integer userId){
        userRepository.deleteById(userId);
    }
}
