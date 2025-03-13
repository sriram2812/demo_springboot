# Vehicle Onboarding API

This Spring Boot application provides a RESTful API for vehicle onboarding with CRUD operations, validation, and comprehensive test coverage.

## Features

- CRUD operations for vehicles
- Input validation
- Exception handling
- Swagger documentation
- Unit tests with 85%+ coverage
- MapStruct for object mapping
- JaCoCo for test coverage reporting

## Prerequisites

- Java 17
- Maven 3.9+

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/sriram2812/demo_springboot.git
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

4. Access the Swagger UI:
   ```
   http://localhost:8080/swagger-ui.html
   ```

## API Endpoints

- POST /api/v1/vehicles - Create a new vehicle
- GET /api/v1/vehicles/{id} - Get a vehicle by ID
- PUT /api/v1/vehicles/{id} - Update a vehicle
- DELETE /api/v1/vehicles/{id} - Delete a vehicle
- GET /api/v1/vehicles/search - Search vehicles with filters

## Testing

Run tests and generate coverage report:
```bash
mvn clean test
```

View the coverage report at: `target/site/jacoco/index.html`