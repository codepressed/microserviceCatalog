# Project

![Microservice image](/assets/img/microservice.png)

This is a microservices built show catalog application built with the following specifications

- JDK: OpenJDK 11
- Framework: Spring / Spring Boot 
- DB: PostgreSQL
- Dependency manager: Maven
- ORM implementation: Hibernate
- Log: Log4j2
- Doc generation from API: springdoc-openapi
- Server HTTP: Tomcat

# Building and running the project

## Building
1st of all JARs should be created from every service. From inside a project running the shell, should be fun:
mvn clean package
and 2 differents JARs will be generated (showcatalog-VERSION.jar & notification-VERSION.jar).

## Running
```
docker compose
```

```
java -jar showcatalog-VERSION.jar
java -jar notification-VERSION.jar
```