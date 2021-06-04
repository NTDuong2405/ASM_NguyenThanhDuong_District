# Sample REST CRUD API with Spring Boot, Mysql, JPA and Hibernate 

## Steps to Setup

**1. Open source code**

```bash
Using IDE open Project
```

**2. Create Mysql database**
```bash
create database db_spring_boot_api
```

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`

+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Build and run the app using maven**

```bash
mvn package
java -jar target/spring-boot-rest-api-tutorial-0.0.1-SNAPSHOT.jar

```

Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

### District

    GET /api/v1/districts
    
    POST /api/v1/districts
    
    GET /api/v1/districts/{districtId}
    
    PUT /api/v1/districts/{districtId}
    
    DELETE /api/v1/districts/{districtId}

### Street

    GET /api/v1/streets
    
    POST /api/v1/streets
    
    GET /api/v1/streets/{streetId}
    
    PUT /api/v1/streets/{streetId}
    
    DELETE /api/v1/streets/{streetId}