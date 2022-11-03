# Rick & Morty :film_strip:
## Test Task

### :performing_arts: ABOUT
This REST-FULL web-app is built using the Spring Boot. 
This application gives data about heroes of the Rick & Morty series. 
All information is stored in its own database and is regularly synchronized with an external resource 
(https://rickandmortyapi.com/api/character). The application takes REST requests and returns responses 
in json format.

### :dart: FEATURES
+ db synchronization with external API
+ API documentation (swagger)
+ provides a random movie character
+ provides a list of movie characters whose contain the given string in their name

### :hammer: TECHNOLOGIES AND FRAMEWORKS
+ Spring Boot
+ Spring Data
+ PostgreSQL
+ JUnit 5
+ HTTP Client
+ Swagger
+ Lombock
+ Log4j2

### :vertical_traffic_light: PROJECT STRUCTURE
The project has an N-Tier Architecture.
+ Model and DTO
> the data for response are mapped into dtos
+ Repository
> all the work with the database takes place at this stage(CRUD methods).
+ Service
> all business logic takes place at the service level
+ Controller
> receives requests from a client and sends responses to them

### :eyes: INSTRUCTIONS FOR LAUNCHING THE PROJECT:
1. Fork this repository
2. Clone the repository to your PC
4. Edit application.properties - set the necessary parameters:
~~~java
        spring.datasource.url="YOUR_DATABASE_URL"
        spring.datasource.username="YOUR_DATABASE_LOGIN"
        spring.datasource.password="YOUR_DATABASE_PASSWORD"
~~~
5. Run project
