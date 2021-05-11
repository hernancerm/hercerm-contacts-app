# Contacts app 👩

This is a single-user contacts CRUD application without authorization nor authentication. It is [deployed in Heroku](https://hercerm-contacts-app.herokuapp.com/), the backend is done with Spring and Java, and the frontend is done with Vue. The project structure is defined by [this starter project](https://github.com/HerCerM/poc-spring-vue).

## Run locally

You need to have installed:

- Maven 3
- Java 11

Then run:

```
mvn clean package
mvn -pl backend spring-boot:run
```

If you don't have Maven installed you can use the wrapper for you OS. For example, if you are in macOS you can use:

```
./mvnw clean package
./mvnw -pl spring-boot:run
```

Then open the project: <http://localhost:8080/>
