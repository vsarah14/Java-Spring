package com.example.CarRenting.controller;

import com.example.CarRenting.business.EmployeeService;
import com.example.CarRenting.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableMethodSecurity
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //C - create operation
    @PostMapping("/employee/createEmployee")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    //R - read operation
    @GetMapping("/employee/readEmployee")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public List<Employee> readEmployee() {
        return employeeService.readEmployee();
    }

    //U - update operation
    @PutMapping("/employee/updateEmployee/{employeeId}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable("employeeId") Integer employeeId) {
        return employeeService.updateEmployee(employee, employeeId);
    }

    //D - delete operation
    @DeleteMapping("/employee/deleteEmployee/{employeeId}")
    @PreAuthorize("hasAuthority('ADMINISTRATOR')")
    public String deleteEmployee(@PathVariable("employeeId") Integer employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }
}
