# TechnicalTestSupraLog

This project is a technical test for SupraLog. It is a Spring Boot application with the following dependencies.

## Dependencies

### Spring Boot Starter Data JPA
- **Group ID:** org.springframework.boot
- **Artifact ID:** spring-boot-starter-data-jpa
- **Description:** Starter for using Spring Data JPA with Hibernate.

### Spring Boot Starter Security
- **Group ID:** org.springframework.boot
- **Artifact ID:** spring-boot-starter-security
- **Description:** Starter for using Spring Security.

### Spring Boot Starter Web
- **Group ID:** org.springframework.boot
- **Artifact ID:** spring-boot-starter-web
- **Description:** Starter for building web, including RESTful, applications using Spring MVC.

### PostgreSQL Driver
- **Group ID:** org.postgresql
- **Artifact ID:** postgresql
- **Scope:** runtime
- **Description:** PostgreSQL JDBC driver for connecting to PostgreSQL databases.

### Spring Boot Starter Test
- **Group ID:** org.springframework.boot
- **Artifact ID:** spring-boot-starter-test
- **Scope:** test
- **Description:** Starter for testing Spring Boot applications with libraries including JUnit, Hamcrest, and Mockito.

### Spring Security Test
- **Group ID:** org.springframework.security
- **Artifact ID:** spring-security-test
- **Scope:** test
- **Description:** Testing utilities for Spring Security.

### Flyway Database Migration
- **Group ID:** org.flywaydb
- **Artifact ID:** flyway-core
- **Description:** Database migration tool for managing and versioning database schema changes.

### Spring Boot Starter Validation
- **Group ID:** org.springframework.boot
- **Artifact ID:** spring-boot-starter-validation
- **Description:** Starter for using the Validation API.

## Architecture Overview

### 1. Controller Layer

- **Responsibility:** Handles HTTP requests related to user operations.
- **Endpoints:**
  -'GET /api/users/{id}': Retrieves user information by user ID.
    #### Request
    - ***Parameters:***
      - `{id}`: Unique identifier of the user.
    
    #### Response
    
    - ***Success Response:***
      - ***Status Code:*** 200 OK
      - ***Body:*** JSON representation of the user information.
    
    - ***Error Response:***
      - ***Status Code:*** 404 Not Found
      - ***Body:*** Error message indicating that the user with the specified ID was not found.

  -'POST /api/users: Creates a new user.'
    #### Request
    - ***Request Body:***
      - **Content Type:*** JSON
      - ***Attributes:***
        - `firstName`: User's first name (string, required).
        - `lastName`: User's last name (string, required).
        - `email`: User's email address (string, required, valid email format).
        - `password`: User's password (string, required).
        - `confirmedPassword`: Confirmation of the user's password (string, required, must match the password).
        - `country`: User's country (enum, required).
        - `birthday`: User's date of birth (string, required, format: "dd/MM/yyyy").
        - `gender`: User's gender (enum, optional).
        - `address`: User's address (string, optional).
        - `phone`: User's phone number (string, optional).
    
    #### Response
    
    - ***Success Response:***
      - ***Status Code:*** 201 Created
      - ***Body:*** Empty body.
    
    - ***Error Response:***
      - ***Status Code:*** 400 Bad Request
      - ***Body:*** JSON representation of validation errors.

## 2. Service Layer

### UserServiceImpl

Responsibility:
- Implements business logic for user operations, including validation.

Methods:
1. `createUser(UserRequest userRequest)`: Validates user input and saves the user to the database.
2. `getUserById(Long userId)`: Retrieves user information by user ID.

## 3. Repository Layer

### UserRepository

Responsibility:
- Extends JpaRepository to provide CRUD operations for UserEntity.

Methods:
1. `findByEmail(String email)`: Finds a user by email.

## 4. Model Layer

### UserEntity

Responsibility:
- Represents the entity for storing user information in the database.

Attributes:
- `id`: Unique identifier for the user.
- `firstName`, `lastName`: User's first and last names.
- `email`: User's email address (unique).
- `password`: Encrypted password.
- `country`: User's country.
- `birthday`: User's date of birth.
- `gender`: User's gender.
- `address`, `phone`: Additional user information.

## 5. DTO Layer

### UserRequest

Responsibility:
- Represents incoming user information for user creation.

Attributes:
- Same as UserEntity except `id`.
- `confirmedPassword`: Used for confirming the entered password.

### UserResponse

Responsibility:
- Represents outgoing user information for user retrieval.

Attributes:
- Same as UserEntity without password.

## 6. Exception Handling

### GlobalExceptionHandler

Responsibility:
- Centralized exception handling for providing consistent error responses.

## 7. Resolving Messages

This project utilizes Spring's `MessageSource` for resolving messages. The messages used in the application are stored in the `messages.properties` file located in the resources folder.

### messages.properties

The `messages.properties` file contains key-value pairs for different messages used throughout the application. This enables easy customization of messages

## 8. Logging

### LoggingAspect

Responsibility:
- An aspect-oriented programming (AOP) aspect to log input, output, and processing time of each method call.

### 9. Database Migration

### db/migration folder

- **Flyway:** put all your database changes in the folder with respect to flyway specifications


## Usage

1. Clone the repository.
2. Build the project using Maven: `mvn clean install`.
3. Run the application: `mvn spring-boot:run`.
4. Access the application at [http://localhost:8080/TECHNICALTEST](http://localhost:8080/TECHNICALTEST).

