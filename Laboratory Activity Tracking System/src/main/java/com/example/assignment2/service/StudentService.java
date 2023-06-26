package com.example.assignment2.service;

import com.example.assignment2.model.Attendance;
import com.example.assignment2.model.Student;
import com.example.assignment2.repository.AttendanceRepository;
import com.example.assignment2.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private AttendanceRepository attendanceRepository;

    //R - read operation
    public List<Student> readStudent() {
        return studentRepository.findAll();
    }

    //U - update operation
    public Student updateStudent(Student student, Integer studentId) {
        Student s = studentRepository.findById(studentId).get();

        if (Objects.nonNull(student.getUsername()) && !"".equalsIgnoreCase(student.getUsername())) {
            s.setUsername(student.getUsername());
        }

        if (Objects.nonNull(student.getPassword()) && !"".equalsIgnoreCase(student.getPassword())) {
            s.setPassword(passwordEncoder.encode(student.getPassword()));
        }

        if (Objects.nonNull(student.getRole()) && !"".equalsIgnoreCase(student.getRole().name())) {
            s.setRole(student.getRole());
        }

        if (Objects.nonNull(student.getEmail()) && !"".equalsIgnoreCase(student.getEmail())) {
            s.setEmail(student.getEmail());
        }

        if (Objects.nonNull(student.getFullName()) && !"".equalsIgnoreCase(student.getFullName())) {
            s.setFullName(student.getFullName());
        }

        if (Objects.nonNull(student.getHobby()) && !"".equalsIgnoreCase(student.getHobby())) {
            s.setHobby(student.getHobby());
        }

        if (Objects.nonNull(student.getGroupNr()) && !"".equalsIgnoreCase(Integer.toString(student.getGroupNr()))) {
            s.setGroupNr(student.getGroupNr());
        }

        if (Objects.nonNull(student.getFullName()) && !"".equalsIgnoreCase(student.getFullName())) {
            s.setFullName(student.getFullName());
        }

        if (Objects.nonNull(student.getAttendance()) && !"".equalsIgnoreCase(student.getAttendance().toString())) {
            s.setAttendance(student.getAttendance());
        }

        return studentRepository.save(s);
    }

    //D - delete operation
    public String deleteStudent(Integer studentId) {
        Student s = studentRepository.findById(studentId).get();
//        List<Attendance> attendances = attendanceRepository.findAll();
//        for (Attendance a : attendances)
//            if (a.getStudent().equals(s)) {
//                return "Student couldn't be deleted.";
//            }
        studentRepository.deleteById(studentId);
        return "Student deleted successfully.";
    }

    public Student findById(int id) {

        var val = studentRepository.findById(id);

        if (val.isPresent()) {
            Student student = val.get();
            return student;
        } else return null;
    }
}
