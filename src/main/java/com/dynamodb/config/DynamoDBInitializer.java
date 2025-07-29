package com.dynamodb.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.dynamodb.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DynamoDBInitializer implements CommandLineRunner {

    private final AmazonDynamoDB amazonDynamoDB;
    private final DynamoDBMapper dynamoDBMapper;

    @Autowired
    public DynamoDBInitializer(AmazonDynamoDB amazonDynamoDB, DynamoDBMapper dynamoDBMapper) {
        this.amazonDynamoDB = amazonDynamoDB;
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public void run(String... args) {
        try {
            CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Employee.class)
                    .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
            
            TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
            
            System.out.println("DynamoDB table for Employee created or already exists.");
        } catch (Exception e) {
            System.err.println("Error creating DynamoDB table for Employee: " + e.getMessage());
            e.printStackTrace();
        }
    }
}