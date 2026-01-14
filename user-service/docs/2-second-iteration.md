## Project structure improvements
- Current structure problem: the project tell us more about the technology stack than about the business logic.
- New structure: apply layered structure based on **Vertical slice architecture (VSA)** with DDD to keep the project maintainable and scalable.
  - Modules by feature
    - `auth/`: authentication and authorization
    - `profile/`: user profile management
    - `shared/`: shared components
  - In each module, we can have the following layers:
    - `Domain`: business logic
      - Entities
      - Interfaces
    - `Application`: application logic
      - DTOs
      - Services
    - `Infrastructure`: infrastructure logic
      - Persistence
    - `Presentation`: presentation logic
      - Controllers
      - Main Application

Flow (dependency direction): Presentation $\rightarrow$ Application $\rightarrow$ Domain $\rightarrow$ Infrastructure

## Quality improvements
- Create a new Entity `User` to manage *username, password, email, birthDate, phoneNumber*
- Create a new Entity `Address` to manage *street, city, country, postalCode*
- Use of real RDBMS for persistence rather than in-memory H2
- Testing: add builder pattern for Entity creation
- Add database migration for
- Use of Value Object (VO) to simplify logic validation
- API Documentation with Swagger UI: SpringDoc OpenAPI
- Containerization the app with Docker
