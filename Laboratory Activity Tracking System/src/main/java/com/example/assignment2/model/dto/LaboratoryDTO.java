package com.example.assignment2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//data transfer object
public class LaboratoryDTO {
    private Integer laboratoryId;
    private Integer number;
    private String date;
    private String title;
    private String description;
    private Integer teacher_id;
    private List<Integer> attendance_id;
}
