package com.example.assignment2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_attendance")
@SequenceGenerator(name = "attendance_start", sequenceName = "attendance_start", initialValue = 3)
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "attendance_start")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private Student student;

    @JsonIgnore
    @ManyToMany(mappedBy = "attendances")
    private List<Laboratory> laboratories = new ArrayList<>();
}
