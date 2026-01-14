# User service
This project is a microservice for user management. It provides authentication and profile management features.

- [Initial decisions](./docs/1-first-iteration.md)
- [Some improvements](./docs/2-second-iteration.md)

## Tech stack
- Programming language: **Java 25 (LTS)**
- Build tool / dependency manager: **Maven v3.9.12**
- Web framework: **Spring Boot 4.0.1**
- Security sub-framework for authentication with JWT configuration: **Spring Security**
- ORM to abstract database access: **Spring Data JPA**
- In-memory database engine: **H2 Database** (don't need to install any RDBMS)
- Testing framework: **JUnit 5 (Jupiter)**
- 3-party dependencies and solutions
  - Data validation: **Jakarta Validation**
  - Code generation (reduce boilerplate code): **Lombok**

## Getting started

### Prerequisites
- **OpenJDK**: recommended +v25
  - Download from [OpenJDK](https://openjdk.java.net/)
  - If you use SDK Manager, run `sdk install java 25-open` to install.
- **Maven**: recommended +v3.9.12
  - Download from [Maven](https://maven.apache.org/install.html)
  - If you use SDK Manager, run `sdk install maven` to install.

### Clone project
```bash
git clone https://github.com/tanvirhosu/playground/tree/java/user-service.git 
cd user-service
```

### Build the app
```bash
mvn clean install
```

### Run the app
- **Configure environment**
  ```bash
  # Copy the example configuration
  cp src/main/resources/application.properties.example src/main/resources/application.properties
  ```
- **Run with Maven**
  ```bash
  mvn spring-boot:run
  ```
- [Open app](http://localhost:8080)

### How to access the H2 database
[H2 Console (development)](http://localhost:8080/h2-console)
- JDBC URL: `jdbc:h2:mem:user_management_db`
- User: `admin`
- Password: `admin`

## API endpoints (`/api/v1`)

### Authentication
- `POST /auth/login`: login and receive JWT

### Profile
- `POST /profile`: create profile
- `GET /profile`: get current user profile
- `PUT /profile`: update profile (full payload)
- `PATCH /profile`: update profile (partial payload)

## Verification

### Manual verification via curl
- [Launch application](http://localhost:8080)
- [H2 console for DB](http://localhost:8080/h2-console)

#### Test: Authentication
**Request**
```bash
# Demo user:  user / password
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "user",
    "password": "password"
  }'
```

**Response**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

<ins>**Note:**</ins> copy the token from the response to use in subsequent requests.

#### Test: Create profile
**Request**
```bash
curl -X POST http://localhost:8080/api/v1/profile \
  -H "Authorization: Bearer <YOUR_TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "tanvir.hossain@example.com",
    "firstName": "Tanvir",
    "lastName": "Hossain",
    "birthDate": "1995-01-01",
    "phoneNumber": "+34678901234",
    "street": "Calle de la Castellana, 1",
    "city": "Madrid",
    "country": "Spain",
    "postalCode": "28001"
  }'
```

**Response**
```json
{
  "userId": "user",
  "email": "tanvir.hossain@example.com",
  "firstName": "Tanvir",
  "lastName": "Hossain",
  "birthDate": "1995-01-01",
  "phoneNumber": "+34678901234",
  "street": "Calle de la Castellana, 1",
  "city": "Madrid",
  "country": "Spain",
  "postalCode": "28001"
}
```

#### Test: Get profile
**Request**
```bash
curl -X GET http://localhost:8080/api/v1/profile \
  -H "Authorization: Bearer <YOUR_TOKEN>"
```

**Response**
```json
{
  "userId": "user",
  "email": "tanvir.hossain@example.com",
  "firstName": "Tanvir",
  "lastName": "Hossain",
  "birthDate": "1995-01-01",
  "phoneNumber": "+34678901234",
  "street": "Calle de la Castellana, 1",
  "city": "Madrid",
  "country": "Spain",
  "postalCode": "28001"
}
```

#### Test: Update profile fully
**Request**
```bash
curl -X PUT http://localhost:8080/api/v1/profile \
  -H "Authorization: Bearer <YOUR_TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "tanvir.hossain.updated@example.com",
    "firstName": "Tanvir Updated",
    "lastName": "Hossain Updated",
    "birthDate": "1990-01-01",
    "phoneNumber": "+34678901235",
    "street": "Calle de la Castellana, 2",
    "city": "Madrid",
    "country": "Spain",
    "postalCode": "28002"
  }'
```

**Response**
```json
{
  "userId": "user",
  "email": "tanvir.hossain.updated@example.com",
  "firstName": "Tanvir Updated",
  "lastName": "Hossain Updated",
  "birthDate": "1990-01-01",
  "phoneNumber": "+34678901235",
  "street": "Calle de la Castellana, 2",
  "city": "Madrid",
  "country": "Spain",
  "postalCode": "28002"
}
```

#### Test: Update profile partially
**Request**
```bash
curl -X PATCH http://localhost:8080/api/v1/profile \
  -H "Authorization: Bearer <YOUR_TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{
    "street": "Calle de la Roma, 1",
    "city": "Barcelona",
    "country": "Spain",
    "postalCode": "08222"
  }'
```

**Response**
```json
{
  "userId": "user",
  "email": "tanvir.hossain.updated@example.com",
  "firstName": "Tanvir Updated",
  "lastName": "Hossain Updated",
  "birthDate": "1990-01-01",
  "phoneNumber": "+34678901235",
  "street": "Calle de la Roma, 1",
  "city": "Barcelona",
  "country": "Spain",
  "postalCode": "08222"
}
``` 

### Automated verification (recommended)
```bash
mvn test
```
