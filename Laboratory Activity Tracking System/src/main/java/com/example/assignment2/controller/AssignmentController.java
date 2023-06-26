package com.example.assignment2.controller;

import com.example.assignment2.model.Assignment;
import com.example.assignment2.model.dto.AssignmentDTO;
import com.example.assignment2.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    //C - create operation
    @PostMapping("/assignment/createAssignment")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Assignment createAssignment(@RequestBody AssignmentDTO assignment) {
        return assignmentService.createAssignment(assignment);
    }

    //R - read operation
    @GetMapping("/assignment/readAssignment")
    @PreAuthorize("hasAuthority('TEACHER')")
    public List<Assignment> readAssignment() {
        return assignmentService.readAssignment();
    }

    //U - update operation
    @PutMapping("/assignment/updateAssignment/{assignmentId}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Assignment updateAssignment(@RequestBody AssignmentDTO assignment, @PathVariable("assignmentId") Integer assignmentId) {
        return assignmentService.updateAssignment(assignment, assignmentId);
    }

    //D - delete operation
    @DeleteMapping("/assignment/deleteAssignment/{assignmentId}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String deleteAssignment(@PathVariable("assignmentId") Integer assignmentId) {
        assignmentService.deleteAssignment(assignmentId);
        return "Assignment deleted successfully.";
    }

    //read assignments for a laboratory
    @GetMapping("/assignment/readAssignmentLaboratory/{laboratoryId}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public List<Assignment> readAssignmentForLab(@PathVariable("laboratoryId") Integer laboratoryId) {
        return assignmentService.readAssignmentForLab(laboratoryId);
    }

}
