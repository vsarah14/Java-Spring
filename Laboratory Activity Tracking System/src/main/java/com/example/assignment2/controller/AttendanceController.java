package com.example.assignment2.controller;

import com.example.assignment2.model.Attendance;
import com.example.assignment2.model.dto.AttendanceDTO;
import com.example.assignment2.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    //C - create operation
    @PostMapping("/attendance/createAttendance")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Attendance createAttendance(@RequestBody AttendanceDTO attendance) {
        return attendanceService.createAttendance(attendance);
    }

    //R - read operation
    @GetMapping("/attendance/readAttendance")
    @PreAuthorize("hasAuthority('TEACHER')")
    public List<Attendance> readAttendance() {
        return attendanceService.readAttendance();
    }

    //U - update operation
    @PutMapping("/attendance/updateAttendance/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public Attendance updateAttendance(@RequestBody AttendanceDTO attendance, @PathVariable("id") Integer attendanceId) {
        return attendanceService.updateAttendance(attendance, attendanceId);
    }

    //D - delete operation
    @DeleteMapping("/attendance/deleteAttendance/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String deleteAttendance(@PathVariable("id") Integer attendanceId) {
        attendanceService.deleteAttendance(attendanceId);
        return "Attendance deleted successfully.";
    }

}
