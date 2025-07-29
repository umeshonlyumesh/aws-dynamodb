package com.dynamodb.resolver;

import com.dynamodb.model.Employee;
import com.dynamodb.model.EmployeeInput;
import com.dynamodb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EmployeeResolver {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeResolver(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @QueryMapping
    public Employee employeeById(@Argument String id) {
        return employeeService.getEmployeeById(id).orElse(null);
    }

    @QueryMapping
    public List<Employee> allEmployees() {
        return employeeService.getAllEmployees();
    }

    @MutationMapping
    public Employee createEmployee(@Argument("employee") EmployeeInput employeeInput) {
        return employeeService.createEmployee(employeeInput.toEmployee());
    }

    @MutationMapping
    public Employee updateEmployee(@Argument String id, @Argument("employee") EmployeeInput employeeInput) {
        Employee employee = employeeInput.toEmployee();
        return employeeService.updateEmployee(id, employee).orElse(null);
    }

    @MutationMapping
    public Boolean deleteEmployee(@Argument String id) {
        return employeeService.deleteEmployee(id);
    }
}
