package com.dynamodb.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.dynamodb.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {

    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public EmployeeRepository(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Employee save(Employee employee) {
        dynamoDBMapper.save(employee);
        return employee;
    }

    public Optional<Employee> findById(String id) {
        Employee employee = dynamoDBMapper.load(Employee.class, id);
        return Optional.ofNullable(employee);
    }

    public List<Employee> findAll() {
        return dynamoDBMapper.scan(Employee.class, new DynamoDBScanExpression());
    }

    public void delete(Employee employee) {
        dynamoDBMapper.delete(employee);
    }

    public void deleteById(String id) {
        findById(id).ifPresent(dynamoDBMapper::delete);
    }
}