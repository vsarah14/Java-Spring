package com.example.assignment2.service;

import com.example.assignment2.model.Submission;
import com.example.assignment2.repository.SubmissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


@Service
@AllArgsConstructor
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    //C - create operation for STUDENT
    public Submission createSubmission(Submission submission) {
        submission.setGrade("No grade");
        return submissionRepository.save(submission);
    }

    //U - update operation for TEACHER
    public Submission updateSubmission(String grade, Integer submissionId) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
        String number;
        int minimum_grade = 0;
        while ((number = reader.readLine()) != null) {
            minimum_grade = Integer.parseInt(number);
        }

        Submission s = submissionRepository.findById(submissionId).get();
        if (Integer.parseInt(grade) > minimum_grade)
            s.setGrade(grade);
        else
            s.setGrade("Grade cannot be lower than 1.");

        return submissionRepository.save(s);
    }

}
