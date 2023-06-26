package com.example.assignment2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_laboratory")
@SequenceGenerator(name = "laboratory_start", sequenceName = "laboratory_start", initialValue = 3)
public class Laboratory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "laboratory_start")
    private Integer laboratoryId;
    private Integer number;
    private String date;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacherId;

    @ManyToMany
    @JoinTable(
            name = "laboratory_attendace",
            joinColumns = @JoinColumn(name = "laboratoryId"),
            inverseJoinColumns = @JoinColumn(name = "attendanceId")
    )
    private List<Attendance> attendances = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "laboratory")
    private List<Assignment> assignments = new ArrayList<>();

}
