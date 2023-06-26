package com.example.assignment2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_submission")
@SequenceGenerator(name = "submission_start", sequenceName = "submission_start", initialValue = 3)
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "submission_start")
    private Integer id;
    private String studentName;
    private String assignmentName;
    private String grade;

}
