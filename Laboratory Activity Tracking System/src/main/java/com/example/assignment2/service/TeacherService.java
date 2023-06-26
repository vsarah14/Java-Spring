package com.example.assignment2.service;

import com.example.assignment2.model.Laboratory;
import com.example.assignment2.model.Teacher;
import com.example.assignment2.repository.LaboratoryRepository;
import com.example.assignment2.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final LaboratoryRepository laboratoryRepository;

    public Teacher findById(int id) {

        var val = teacherRepository.findById(id);

        if (val.isPresent()) {
            Teacher teacher = val.get();
            return teacher;
        } else return null;
    }

    private final PasswordEncoder passwordEncoder;

    //C - create operation
    public Teacher createStudent(Teacher teacher) {
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        return teacherRepository.save(teacher);
    }

    //R - read operation
    public List<Teacher> readTeacher() {
        return teacherRepository.findAll();
    }

    //U - update operation
    public Teacher updateTeacher(Teacher teacher, Integer teacherId) {
        Teacher t = teacherRepository.findById(teacherId).get();

        if (Objects.nonNull(teacher.getUsername()) && !"".equalsIgnoreCase(teacher.getUsername())) {
            t.setUsername(teacher.getUsername());
        }

        return teacherRepository.save(t);
    }

    //D - delete operation
    public String deleteTeacher(Integer teacherId) {

        Teacher t = teacherRepository.findById(teacherId).get();
        List<Laboratory> labs = laboratoryRepository.findAll();
        for (Laboratory l : labs)
            if (l.getTeacherId().equals(t)) {
                return "Teacher couldn't be deleted.";
            }
        teacherRepository.deleteById(teacherId);
        return "Teacher deleted successfully.";
    }
}
