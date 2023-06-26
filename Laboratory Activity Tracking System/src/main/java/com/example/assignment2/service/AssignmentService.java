package com.example.assignment2.service;

import com.example.assignment2.model.Assignment;
import com.example.assignment2.model.Laboratory;
import com.example.assignment2.model.dto.AssignmentDTO;
import com.example.assignment2.repository.AssignmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;
    private LaboratoryService laboratoryService;

    public Assignment toEntity(AssignmentDTO assignment) {

        Laboratory lab = laboratoryService.findById(assignment.getLaboratory_id());
        return Assignment.builder()
                .assignmentId(assignment.getAssignmentId())
                .name(assignment.getName())
                .deadline(assignment.getDeadline())
                .description(assignment.getDescription())
                .laboratory(lab)
                .build();
    }

    public AssignmentDTO fromEntity(Assignment assignment) {

        return AssignmentDTO.builder()
                .assignmentId(assignment.getAssignmentId())
                .name(assignment.getName())
                .deadline(assignment.getDeadline())
                .description(assignment.getDescription())
                .laboratory_id(assignment.getLaboratory().getLaboratoryId())
                .build();
    }

    //C - create operation
    public Assignment createAssignment(AssignmentDTO assignment) {
        Assignment a = toEntity(assignment);
        return assignmentRepository.save(a);
    }

    //R - read operation
    public List<Assignment> readAssignment() {
        return assignmentRepository.findAll();
    }

    //U - update operation
    public Assignment updateAssignment(AssignmentDTO assignment, Integer assignmentId) {
        Assignment ass = assignmentRepository.findById(assignmentId).get();
        AssignmentDTO a = fromEntity(ass);


        if (Objects.nonNull(assignment.getName()) && !"".equalsIgnoreCase(assignment.getName())) {
            a.setName(assignment.getName());
        }

        if (Objects.nonNull(assignment.getDeadline()) && !"".equalsIgnoreCase(assignment.getDeadline())) {
            a.setDeadline(assignment.getDeadline());
        }

        if (Objects.nonNull(assignment.getDescription()) && !"".equalsIgnoreCase(assignment.getDescription())) {
            a.setDescription(assignment.getDescription());
        }

        if (Objects.nonNull(assignment.getLaboratory_id()) && !"".equalsIgnoreCase(assignment.getLaboratory_id().toString())) {
            a.setLaboratory_id(assignment.getLaboratory_id());
        }

        return assignmentRepository.save(toEntity(a));
    }

    //D - delete operation
    public void deleteAssignment(Integer assignmentId) {
        assignmentRepository.deleteById(assignmentId);
    }

    public List<Assignment> readAssignmentForLab(Integer labId) {
        Laboratory lab = laboratoryService.findById(labId);
        List<Assignment> list = assignmentRepository.findAll();
        List<Assignment> finalList = new ArrayList<>();
        for (Assignment assignment : list)
            if (assignment.getLaboratory().equals(lab)) {
                finalList.add(assignment);

            }
        return finalList;
    }
}
