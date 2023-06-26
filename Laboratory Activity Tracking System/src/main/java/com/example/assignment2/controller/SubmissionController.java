package com.example.assignment2.controller;

import com.example.assignment2.model.Submission;
import com.example.assignment2.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@EnableMethodSecurity
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    //C - create operation
    @PostMapping("/submission/createSubmission")
    @PreAuthorize("hasAuthority('STUDENT')")
    public Submission createSubmission(@RequestBody Submission submission) {
        return submissionService.createSubmission(submission);
    }

    //U - update operation
    @PutMapping("/submission/updateSubmission/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Submission updateUser(@RequestBody Submission submission, @PathVariable("id") Integer submissionId) throws IOException {
        String grade = submission.getGrade();
        return submissionService.updateSubmission(grade, submissionId);
    }
}
