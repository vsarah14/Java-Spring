package com.example.assignment2.repository;

import com.example.assignment2.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Integer>, CrudRepository<Submission, Integer> {

}
