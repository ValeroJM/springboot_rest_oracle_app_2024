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

## Session 2
For this session I have created 2 folders and 2 Interfaces + 1 Class
1. Folder: repo > CourseRepository (Interface)
   * CourseRepository Interface: This class will **extends JpaRepository<Course, Serializable>**
   * I am wondering why we do NOT extends JpaRepository in this way in the video: 
   ```
   @Repository
   public interface CourseRepository extends JpaRepository<Course, Long> {
   }
   ```
2. Folder: service > CourseService (Interface)
    * We created a method using upsert -- It will be use to create and update
    * We created a getById -- This method will retrieve a desire couser using Id
    * We created a getAllCourses -- This method will retrieve all the available courses
    * We created a deleteById -- This method will delete a desire method using Id

3. Folder: service > CourseServiceImp (Class)
    * This class **implements CourseService** (The interface will be injected and add during run time)
    * We add @Service annotation on top of the class
    * We add @Autowired annotation and instantiate the CourseRepository Interface. It will inject the repository interface into the Implementation service.
    * We implemented all the methods we created in CourseSevice Interface
      * Upsert Method: We use the autowired courseRepository.save() -- To insert / update a record
      * GetById Method: It will store the DB Entity into an Optional. Then we validate that the object is Present, if it is present then we return it if not it will return null
      * GetAllCourses Method: It will return all the Courses available in the database.
      * DeleteById Method: It will validate if that record exist in the database then It will use deleteById(id). It the record does NOT exist then it will return no record found

## Session 3
In this session we created a folder call rest and 1 class call CourseRestController where will inject our Service Interface and create our API mapping.
We followed the next steps:
1. We used @RestController annotation to mark this class as RestController
2. We instantiated our CourseService as private and annotate it as @Autowired to inject our Service into our Controller
3. We created a response for each method we created in our Service interface using the necessary annotation like: @PostMapping or @GetMapping or @PutMapping or @DeleteMapping
4. We completed the implementation of the methods @PathVariable is a Variable is needed or @RequestBody (An object will be required) for @PostMapping
5. In the return we added a ResponseEntity with 2 parameters: Status and HttpStatus + desire status