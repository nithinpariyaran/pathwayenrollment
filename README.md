Spring Boot, MySQL, JPA, Hibernate Rest API Tutorial
Build Restful CRUD API for a  Student Enrollment application using Spring Boot, Mysql, JPA.

Requirements
Java - 1.8.x

Maven - 3.x.x

Mysql - 5.x.x

Steps to Setup
1. Clone the application

git clone https://github.com/nithinpariyaran/pathwayenrollment.git
2. Create Mysql database

create database connections
3. Change mysql username and password as per your installation

open src/main/resources/application.properties

change spring.datasource.username and spring.datasource.password as per your mysql installation

4. Build and run the app using maven

mvn package
java -jar target/
Alternatively, you can run the app without packaging it using -

mvn spring-boot:run
The app will start running at http://localhost:5000.

Explore Rest APIs
The app defines following CRUD APIs.

GET /api/v1/students

POST /api/v1/addStudent

GET /api/v1/getStudent{studentId}

GET /api/v1/getStudentForClass{className}

PUT /api/v1/updateStudent

DELETE /api/v1/deleteStudent/{id}

POST /api/v1/addUser

You can test them using postman or any other rest client.
