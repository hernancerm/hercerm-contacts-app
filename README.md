# Proof of concept: Spring Boot and Vue application

## Executing the project

On the root of the parent Maven project, run: 

```txt
mvn clean install
mvn -pl backend spring-boot:run
```

## Application's behavior

Once the application is up and running, the following is true:

- Going to `localhost:8098` serves Vue's home page.
- Route navigation through page components (e.g. links) works.
- Route navigation by directly typing in browser nav bar works.
- The frontend can talk with the backend without CORS config.
  
Both the frontend and the backend exist in the same origin.

## Setup overview

The project is a two-module Maven project; one sub-module for the backend, another one for the frontend.

The idea for having both the frontend and backend in the **same origin** boils down to having the backend serve the SPA's (Single Page Application) entry file (`index.html`) and let the SPA deal with everything frontend related (data fetching, state management, routing, etc.), whilst the backend merely forwards non-API requests to the SPA and attends API requests.

### How to make Spring Boot serve the SPA's `index.html`

By default, when the root URL is visited, Spring Boot serves the following file:

```txt
src/resources/public/index.html
```

By default, when a Vue application is built, it outputs the following directory:

```
dist
├── css/
├── img/
├── js/
├── favicon.ico
└── index.html
```

The idea is to copy the output of Vue's build into the place where Spring Boot expects to find the `index.html`. This is done with the [`maven-resources-plugin`](https://maven.apache.org/plugins/maven-resources-plugin/) (see the `pom.xml` of the backend sub-module). This plugin does the copying during the `generate-resources` phase, which comes before the `compile` phase. For reference on the Maven phases, see [Lifecycles Reference](https://maven.apache.org/ref/3.6.3/maven-core/lifecycles.html).

Additionally, how can the frontend be built by Maven? Otherwise, the frontend would be required to be built through a npm invocation before Maven can use the output. This is done with the [`frontend-maven-plugin`](https://github.com/eirslett/frontend-maven-plugin) (see the `pom.xml` of the frontend sub-module).

### Other configurations

- **Vue's dev server**. With the setup described above, running the project requires Maven to re-build the frontend and re-compile the backend, which can be lengthy. To accelerate frontend development, a dev server is created at `localhost:8080` after running `npm run serve` in the frontend sub-module. **This does require CORS to talk to the backend**, but frontend reload is much faster. CORS is configured in Spring Boot for the dev server (see class `WebConfiguration`).
- **Spring Boot's route forwarding**. Spring Boot automatically serves `src/resources/public/index.html` when the root URL is visited, however it does not automatically delegate routing to Vue. To allow Vue to handle all the routing, forwarding is required for every route except `/index.html` and `api/*` routes.