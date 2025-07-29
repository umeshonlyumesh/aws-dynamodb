# DynamoDB GraphQL CRUD API

This project demonstrates a CRUD API for an Employee entity using AWS DynamoDB and GraphQL with Spring Boot.

## Prerequisites

- Java 17
- Maven
- DynamoDB Local (for local development)

## Setup

1. Clone the repository
2. Run DynamoDB Local:
   ```
   java -Djava.library.path=./DynamoDBLocal_lib -jar DynamoDBLocal.jar -sharedDb
   ```
3. Build and run the application:
   ```
   mvn spring-boot:run
   ```

## GraphQL API

The GraphQL API is available at `http://localhost:8080/graphql`. You can use GraphiQL to interact with the API at `http://localhost:8080/graphiql`.

### Queries

- Get an employee by ID:
  ```graphql
  query {
    employeeById(id: "123") {
      id
      firstName
      lastName
      email
      department
      position
      salary
    }
  }
  ```

- Get all employees:
  ```graphql
  query {
    allEmployees {
      id
      firstName
      lastName
      email
      department
      position
      salary
    }
  }
  ```

### Mutations

- Create an employee:
  ```graphql
  mutation {
    createEmployee(employee: {
      firstName: "John"
      lastName: "Doe"
      email: "john.doe@example.com"
      department: "Engineering"
      position: "Software Engineer"
      salary: 100000
    }) {
      id
      firstName
      lastName
      email
      department
      position
      salary
    }
  }
  ```

- Update an employee:
  ```graphql
  mutation {
    updateEmployee(id: "123", employee: {
      firstName: "Jane"
      lastName: "Doe"
      email: "jane.doe@example.com"
      department: "Engineering"
      position: "Senior Software Engineer"
      salary: 120000
    }) {
      id
      firstName
      lastName
      email
      department
      position
      salary
    }
  }
  ```

- Delete an employee:
  ```graphql
  mutation {
    deleteEmployee(id: "123")
  }
  ```

## Project Structure

- `model`: Contains the Employee entity and EmployeeInput classes
- `config`: Contains DynamoDB configuration and initialization
- `repository`: Contains the EmployeeRepository for DynamoDB operations
- `service`: Contains the EmployeeService for business logic
- `resolver`: Contains the GraphQL resolvers for the operations
- `resources/graphql`: Contains the GraphQL schema

## Configuration

The application is configured to use DynamoDB Local by default. You can modify the configuration in `application.properties` to use a real DynamoDB instance.