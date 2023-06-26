package com.example.assignment2.repository;

import com.example.assignment2.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer>, CrudRepository<Attendance, Integer> {
}
