package com.example.assignment1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data                    //bundles the features of toString, getter, setter, constructor
@Builder                 //to create/build instances of our class
@NoArgsConstructor       //generates a constructor with no parameters
@AllArgsConstructor      //generates a constructor with 1 parameter for each field in the class
@Entity                  //a class that is correlated with a table in a database
@Table(name = "_user")   //our table is called _user
@SequenceGenerator(name = "user_start", sequenceName = "user_start", initialValue = 6)
public class User implements UserDetails {    //the interface provides user information
    //has some methods that need to be implemented

    @Id                   //it indicates that the member below is the primary key of the current entity
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_start")
    //if we want to automatically generate the primary key(AUTO - default generation type)
    private Integer id;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)     //represents a particular column in the database
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    //the methods below must be true in order to connect to our users
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
