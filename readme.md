# Caravelo Route Network

## Purpose
This application, developed using **Java 17**, is designed to Route mapping API service to keep up to date the route information system and inform us about any change into their network.
The application is multi

---

## Technology Stack
The project leverages the following technologies, frameworks, and libraries:
- **Java 17**: Core programming language.
- **Spring Boot**: Framework to simplify application development and deployment.
- **Lombok**: To reduce boilerplate code for model classes.
- **MapStruct**: For efficient and type-safe object mapping.
- **Log4j**: Logging framework for monitoring and debugging.
- **JPA (Hibernate)**: To handle object-relational mapping and database operations.
- **MySQL**: Relational database management system for storing data.

---

## Multitenancy Design
The application is built as a **multitenant system** using a shared database model. Tenants are differentiated by a specific **field** rather than separate schemas, which optimizes database utilization and reduces complexity. The tenant identifier is sent in the **HTTP headers** of incoming requests to ensure accurate data separation.

### Future Improvement
In future iterations, the application aims to implement **authorization tokens**, allowing the tenant information to be extracted directly from the JWT rather than HTTP headers, enhancing security and scalability.

---

## Application Properties
The `application.properties` file includes the following key properties:
1. **Database Configuration**: Host, port, username, password, and schema details for connecting to the MySQL database.
2. **Logging Settings**: Configuration to control logging levels and formats using Log4j.
3. **API Integration**: Endpoint and authentication details for integrating with external APIs like the Google API.
4. **Server Settings**: Port number and other server-specific configurations.

---

## Challenges Faced
During the development, the team encountered several challenges:
- **Google API Login**: Integrating with Google's authentication API proved to be complex and required multiple iterations to resolve issues with OAuth tokens and callback handling. Finally we get the following error on oAuth2: 
 
_You cannot log in to this application because it does not comply with Google's OAuth 2.0 policy.
If you are the developer of the application, register the redirect URI in the Google Cloud Console.

Si eres el desarrollador de la aplicación, comprueba que los datos de esta solicitud cumplan las políticas de Google.

    redirect_uri: https://script.google.com/macros/s/AKfycbxRNENzlOcpCkzuPQvurSoqI0MTk1qgyOuBqdVyKF-B80BjKes-R0I4pbLnqCGBYaBu3g/exec/SalaRemates.svc/JSON/GetStation/en-US
    flowName: GeneralOAuthFlow_

For this reason, the responses get from the sample are hosted in other server.

---

## Planned Improvements
For future releases, the following improvements are being considered:
- **Hexagonal Architecture**: To further decouple application components and improve maintainability.
- **Enhanced Filtering**: Implementing more accurate and efficient filtering mechanisms.
- **Spring Security**: Implementing JWT access token.
- **Pageable**: Return a Page instead of raw entity json data to allow pagination to frontend.

---

## Dockerization
The application is containerized using Docker to ensure consistent environments and simplify deployments. 
Below is the key content of the `Dockerfile`:
```dockerfile
# Base image
FROM openjdk:17-jdk-alpine

# Working directory setup
WORKDIR /app

# Copy application JAR
COPY routenet-api/target/*.jar app.jar
COPY routenet-api/src/main/resources/application.properties application.properties

# Expose the application port
EXPOSE 8080

# Entry point to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
