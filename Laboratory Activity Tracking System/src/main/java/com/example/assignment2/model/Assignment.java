package com.example.assignment2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_assignment")
@SequenceGenerator(name = "assignment_start", sequenceName = "assignment_start", initialValue = 3)
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "assignment_start")
    private Integer assignmentId;
    private String name;
    private String deadline;
    private String description;

    @ManyToOne
    @JoinColumn(name = "laboratory_id")
    private Laboratory laboratory;
}
