## Project structure
Structure based on simplicity rather than complexity using some SoC layers.
- `controller/`: API endpoints and request handling
- `service/`: core business logic and service orchestration
- `repository/`: data access layer for persistence
- `model/`: database entities and domain models
- `security/`: security concerns like authentication and authorization
- `exception/`: error handling
- `dto/`: data transfer objects for API contracts in order from decouple internal entities

Flow: Controller $\rightarrow$ Service $\rightarrow$ Repository $\rightarrow$ Database

## Notes
- Spring Boot + Maven + SoC (Controller, Service, Repository, Model, DTO).
- API versioning: using path prefix through URI
- Authentication with JWT: stateless authentication using Bearer tokens
  - Security: Stateless JWT authentication using Spring Security
  - For now we will use in-memory or hardcoded user
- Profile Management
- Persistence: H2 Database (in-memory)
- Validation: Jakarta validation for simplicity
- Error Handling: exception handler for consistent error responses

## References
- [Maven](https://maven.apache.org/guides/index.html)
- [Spring Boot with Maven plugin](https://docs.spring.io/spring-boot/maven-plugin/getting-started.html)
- [Spring Web Framework with MVC approach](https://docs.spring.io/spring-boot/4.0.1/reference/web/servlet.html)
- [Spring Security](https://docs.spring.io/spring-boot/4.0.1/reference/web/spring-security.html)
- [Spring Data JPA](https://docs.spring.io/spring-boot/4.0.1/reference/data/sql.html#data.sql.jpa-and-spring-data)
- Some examples using Spring Boot
  - [REST services tutorial](https://spring.io/guides/tutorials/rest/)
  - [RESTful Web Service](https://spring.io/guides/gs/rest-service/)
  - [Serving Web Content](https://spring.io/guides/gs/serving-web-content/)
  - [Securing Web](https://spring.io/guides/gs/securing-web/)
  - [Authenticating LDAP](https://spring.io/guides/gs/authenticating-ldap/)
  - [OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
  - [Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
  - [Jakarta validation](https://spring.io/guides/gs/validating-form-input/)
