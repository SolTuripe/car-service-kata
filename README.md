# Car service 🚙

TDD practice. Build a Car Renting Service

🚥 Test → Red → Green → Refactor 🔁

## Develop 📌

```
Start a project at Spring Initializr
Or clone/fork this repo
Run the tests to see them pass!
```

## Tests 🚦

<p align="center">
<img src="./src/carTest.png" width=70%>
</p>

## Rules 📋

- Create a service that once it receives a user id and a car id, it creates, saves and returns a new rental item.
- The user must have: id and name.
- The car must have: id, number plate and brand.
- The rental item must contain: id, user, car and rental date.
- The rental can only be made if the car in question is not rented.

## Constraints 🚨

- Do the practice with TDD
- Remember that it is a unit test of the service so if you cannot raise databases or Api Controllers.
- Solve with dependency injection the extra classes you need (Do not use Autowire)
- Use good practices such as naming (in English).

## Tech Stack & Tools

- [Spring Boot](https://spring.io/projects/spring-boot)
- [IntelliJ Idea](https://www.jetbrains.com/idea/) or any other IDE you like
- JAVA
- Mock
- Exceptions