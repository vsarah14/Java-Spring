package com.example.assignment2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDTO {
    private Integer assignmentId;
    private String name;
    private String deadline;
    private String description;
    private Integer laboratory_id;

}
