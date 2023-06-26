package com.example.assignment1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_band")
public class Band {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String genre;

    @ManyToMany
    private List<Concert> concerts = new ArrayList<>();

}
