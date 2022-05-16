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
- Swagger

# Building and running the project

## Setting up Microservices

Setting up the microservices:
```
docker compose
```

## Setting up Java project

Downloading and running Java project:
```
cd epcsd-spring-showcatalog-main
mvn clean install
java -jar showcatalog-VERSION.jar

cd epcsd-spring-notification-main
mvn clean install
java -jar notification-VERSION.jar
```

## Urls to test the API:
![Swagger image](/assets/img/swagger.png)
Swagger: http://localhost:18081/swagger-ui/index.html

![OpenApi](/assets/img/openapi.png)
OpenApi: http://localhost:18081/v3/api-docs/

## Postman
![PostMan](/assets/img/postman.png)
Official website: https://www.postman.com/
