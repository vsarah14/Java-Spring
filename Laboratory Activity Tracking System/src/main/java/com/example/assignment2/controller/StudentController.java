package com.example.assignment2.controller;

import com.example.assignment2.model.Student;
import com.example.assignment2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity
public class StudentController {

    @Autowired
    private StudentService studentService;

    //R - read operation
    @GetMapping("/students/readStudent")
    @PreAuthorize("hasAuthority('TEACHER')")
    public List<Student> readStudent() {
        return studentService.readStudent();
    }

    //U - update operation
    @PutMapping("/students/updateStudent/{studentId}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Student updateStudent(@RequestBody Student student, @PathVariable("studentId") Integer studentId) {
        return studentService.updateStudent(student, studentId);
    }

    //D - delete operation
    @DeleteMapping("/students/deleteStudent/{studentId}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String deleteStudent(@PathVariable("studentId") Integer studentId) {
        return studentService.deleteStudent(studentId);
    }

}
