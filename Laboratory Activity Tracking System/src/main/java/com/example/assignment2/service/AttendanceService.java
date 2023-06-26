package com.example.assignment2.service;

import com.example.assignment2.model.*;
import com.example.assignment2.model.dto.AttendanceDTO;
import com.example.assignment2.repository.AttendanceRepository;
import com.example.assignment2.repository.LaboratoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AttendanceService {


    @Autowired
    private AttendanceRepository attendanceRepository;

    private StudentService studentService;

    private LaboratoryRepository laboratoryRepository;

    public Attendance toEntity(@Lazy AttendanceDTO attendance) {

        Student student = studentService.findById(attendance.getStudent_id());
        return Attendance.builder()
                .id(attendance.getId())
                .student(student)
                .build();
    }

    public AttendanceDTO fromEntity(@Lazy Attendance attendance) {

        return AttendanceDTO.builder()
                .id(attendance.getId())
                .student_id(attendance.getStudent().getStudentId())
                .build();
    }

    //C - create operation
    public Attendance createAttendance(AttendanceDTO attendance) {
        Attendance a = toEntity(attendance);
        return attendanceRepository.save(a);
    }

    //R - read operation
    public List<Attendance> readAttendance() {
        return attendanceRepository.findAll();
    }

    //U - update operation
    public Attendance updateAttendance(AttendanceDTO attendance, Integer attendanceId) {
        Attendance att = attendanceRepository.findById(attendanceId).get();
        AttendanceDTO a = fromEntity(att);

        if (Objects.nonNull(attendance.getStudent_id()) && !"".equalsIgnoreCase(attendance.getStudent_id().toString())) {
            a.setStudent_id(attendance.getStudent_id());
        }

        return attendanceRepository.save(toEntity(a));
    }

    //D - delete operation
    public String deleteAttendance(Integer attendanceId) {
        Attendance att = attendanceRepository.findById(attendanceId).get();
        List<Laboratory> laboratories = laboratoryRepository.findAll();
        for (Laboratory l : laboratories)
            if (l.getAttendances().equals(att)) {
                return "Attendance couldn't be deleted.";
            }
        attendanceRepository.deleteById(attendanceId);
        return "Attendance deleted successfully.";
    }

    public List<Attendance> findAllByIds(List<Integer> attendanceId) {
        List<Attendance> attendanceList = new ArrayList<>();
        List<Attendance> allAttendances = attendanceRepository.findAll();

        for (Attendance attendance : allAttendances) {
            if (attendanceId.contains(attendance.getId())) {
                attendanceList.add(attendance);
            }
        }

        return attendanceList;
    }
}
