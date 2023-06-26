package com.example.assignment2.service;

import com.example.assignment2.auth.AuthenticationRequest;
import com.example.assignment2.auth.AuthenticationResponse;
import com.example.assignment2.auth.RegisterRequest;
import com.example.assignment2.configuration.JwtService;
import com.example.assignment2.model.Role;
import com.example.assignment2.model.Student;
import com.example.assignment2.model.Teacher;
import com.example.assignment2.repository.StudentRepository;
import com.example.assignment2.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService service;
    private final AuthenticationManager manager;

    //for student - create and login
    public AuthenticationResponse createStudent(Student request) {
        var student = Student.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .fullName(request.getFullName())
                .groupNr(request.getGroupNr())
                .hobby(request.getHobby())
                .role(Role.STUDENT)
                .build();
        studentRepository.save(student);
        var token = service.generateToken(student);
        return AuthenticationResponse.builder().token(token).build();

    }

    public AuthenticationResponse loginStudent(AuthenticationRequest request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = studentRepository.findByUsername(request.getUsername()).orElseThrow();
        var token = service.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();

    }

    //for teacher - register and login
    public AuthenticationResponse registerTeacher(RegisterRequest request) {
        var teacher = Teacher.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.TEACHER)
                .email(request.getEmail())
                .fullName(request.getFullName())
                .build();
        teacherRepository.save(teacher);
        var token = service.generateToken(teacher);
        return AuthenticationResponse.builder().token(token).build();
    }

    public AuthenticationResponse loginTeacher(AuthenticationRequest request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = teacherRepository.findByUsername(request.getUsername()).orElseThrow();
        var token = service.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();

    }
}
