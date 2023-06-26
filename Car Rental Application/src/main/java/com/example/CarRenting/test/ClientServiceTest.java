package com.example.CarRenting.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.CarRenting.business.EmployeeService;
import com.example.CarRenting.model.Employee;
import com.example.CarRenting.model.Role;
import com.example.CarRenting.persistance.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClientServiceTest {

    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Test
    public void testCreateEmployee(){
        Employee employee = new Employee(1, "razvan", "linux", "razvan@linux", "Razvan Bolundut", Role.EMPLOYEE);
        when(employeeRepository.save(employee)).thenReturn(employee);
        Employee savedEmployee = employeeService.createEmployee(employee);
        assertEquals(employee,savedEmployee);
    }

}
