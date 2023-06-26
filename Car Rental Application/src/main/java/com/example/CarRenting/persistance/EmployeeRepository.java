package com.example.CarRenting.persistance;

import com.example.CarRenting.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>, CrudRepository<Employee, Integer> {
    Optional<Employee> findByUsername(String username);
}
