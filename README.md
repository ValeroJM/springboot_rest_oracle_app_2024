# springboot_rest_oracle_app_2024
I am trying to improve my knowledge using:
* Spring Boot
* JPA (Java Persistence API)
* Oracle Database in a container
* Using Postman with CRUD operations

I am using this Youtube course to do this project:

[Spring Boot Project Development : REST API + Data JPA + MySQL DB](https://www.youtube.com/watch?v=_rOUDhCE-x4)

## Session 1
1. I have configured the Oracle Database that is running a container configuring the **application.yml** file
Here is how the configuration looks like:
```
spring:
  datasource:
    username: system
    password: my-password
    url: jdbc:oracle:thin:@localhost:1521/free
    driver-class-name: oracle.jdbc.OracleDriver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```
2. I have created a folder call binding where Course class was created with the different fields that we are going to use for this project.
I used several annotation that will be used for the project:
* @Data -- This annotation is used for Logbook library to generate getter and setter (In the future I will try to use a Record class instead and see the results)
* @Entity -- To identify the class as database Entity
* @Table(name = "COURSE_DTLS") -- This annotation will generate dynamically the new table in the Database with the provided name. If NO name is provided an only @Table is present then the name of the Class will be used as table name in our case Course
* @Id -- To identify the primary Key of the table
* @GeneratedValue(strategy = strategy = GenerationType.IDENTITY) -- To generate dynamically the primary key

<code style="color : red">Attention!</code>
When you create the variables you need for your database entity make sure that you are using Java Wrapper Classes otherwise the table will NOT be created in your Oracle database.
Double check you are using Long, Double...
