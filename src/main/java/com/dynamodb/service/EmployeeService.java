package com.dynamodb.service;

import com.dynamodb.model.Employee;
import com.dynamodb.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        if (employee.getId() == null || employee.getId().isEmpty()) {
            employee.setId(UUID.randomUUID().toString());
        }
        return employeeRepository.save(employee);
    }

    public Optional<Employee> getEmployeeById(String id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> updateEmployee(String id, Employee employeeDetails) {
        return employeeRepository.findById(id)
                .map(existingEmployee -> {
                    if (employeeDetails.getFirstName() != null) {
                        existingEmployee.setFirstName(employeeDetails.getFirstName());
                    }
                    if (employeeDetails.getLastName() != null) {
                        existingEmployee.setLastName(employeeDetails.getLastName());
                    }
                    if (employeeDetails.getEmail() != null) {
                        existingEmployee.setEmail(employeeDetails.getEmail());
                    }
                    if (employeeDetails.getDepartment() != null) {
                        existingEmployee.setDepartment(employeeDetails.getDepartment());
                    }
                    if (employeeDetails.getPosition() != null) {
                        existingEmployee.setPosition(employeeDetails.getPosition());
                    }
                    if (employeeDetails.getSalary() != null) {
                        existingEmployee.setSalary(employeeDetails.getSalary());
                    }
                    return employeeRepository.save(existingEmployee);
                });
    }

    public boolean deleteEmployee(String id) {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}