package com.example.CarRenting.business;

import com.example.CarRenting.model.Employee;
import com.example.CarRenting.persistance.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //C - create operation
    public Employee createEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    //R - read operation
    public List<Employee> readEmployee() {
        return employeeRepository.findAll();
    }

    //U - update operation
    public Employee updateEmployee(Employee employee, Integer employeeId) {
        Employee e = employeeRepository.findById(employeeId).get();

        if (Objects.nonNull(employee.getUsername()) && !"".equalsIgnoreCase(employee.getUsername())) {
            e.setUsername(employee.getUsername());
        }

        if (Objects.nonNull(employee.getPassword()) && !"".equalsIgnoreCase(employee.getPassword())) {
            e.setPassword(passwordEncoder.encode(employee.getPassword()));
        }

        if (Objects.nonNull(employee.getEmail()) && !"".equalsIgnoreCase(employee.getEmail())) {
            e.setEmail(employee.getEmail());
        }

        if (Objects.nonNull(employee.getFullName()) && !"".equalsIgnoreCase(employee.getFullName())) {
            e.setFullName(employee.getFullName());
        }

        return employeeRepository.save(e);
    }

    //D - delete operation
    public String deleteEmployee(Integer employeeId) {
        employeeRepository.deleteById(employeeId);
        return "Employee deleted successfully.";
    }

}
