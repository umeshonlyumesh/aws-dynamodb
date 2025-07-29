package com.dynamodb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInput {
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String position;
    private Double salary;

    public Employee toEmployee() {
        return Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .department(department)
                .position(position)
                .salary(salary)
                .build();
    }
}