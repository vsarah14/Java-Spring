package com.example.assignment1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_concert")
@SequenceGenerator(name = "concert_start", sequenceName = "concert_start", initialValue = 6)
public class Concert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "concert_start")
    private Integer id;

    private String name;
    private Integer tickets;
    private String date;
    @ManyToMany
    private List<Band> bands = new ArrayList<>();


}
