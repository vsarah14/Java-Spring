package com.example.assignment2.controller;

import com.example.assignment2.model.Teacher;
import com.example.assignment2.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity
public class TeacherController {
    @Autowired
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    //C - create operation
    @PostMapping("/teacher/createTeacher")
    public Teacher createStudent(@RequestBody Teacher teacher) {
        return teacherService.createStudent(teacher);
    }

    //R - read operation
    @GetMapping("/teacher/readTeacher")
    public List<Teacher> readStudent() {
        return teacherService.readTeacher();
    }

    //U - update operation
    @PutMapping("/teacher/updateTeacher/{id}")
    public Teacher updateUser(@RequestBody Teacher teacher, @PathVariable("id") Integer teacherId) {
        return teacherService.updateTeacher(teacher, teacherId);
    }

    //D - delete operation
    @DeleteMapping("/teacher/deleteTeacher/{id}")
    public String deleteUser(@PathVariable("id") Integer teacherId) {
        return teacherService.deleteTeacher(teacherId);
    }
}
