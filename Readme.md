# Quarkus Multi-Module Quickstart

This project is a modular Quarkus starter that separates application concerns across multiple Maven modules. It follows clean architecture principles and supports scalable, maintainable development.

## Project Structure

```
├── .mvn/
├── .vscode/
├── app/
├── core/
├── service/
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

### Module Roles

- **app**: The application launcher. It declares dependencies to all other modules and is the only module used to run the app.
- **core**: Pure Java, no Quarkus or third-party runtime dependencies. It contains domain models, ports (interfaces), and business logic.
- **service**: Quarkus-aware implementation of services. This module uses Quarkus extensions and implements the interfaces defined in core.

## Dependency Management

All shared versions and plugin configurations are declared in the parent `pom.xml` using `<dependencyManagement>`. This centralizes version control and avoids repetition across modules.

To declare dependencies in child modules:

```xml
<dependencies>
  <dependency>
    <groupId>com.example</groupId>
    <artifactId>core</artifactId>
  </dependency>
</dependencies>
```

## Quarkus and Bean Discovery

Quarkus uses build-time bean discovery to optimize startup time. In a multi-module setup, it only indexes beans declared in the main module and its direct dependencies.

To ensure Quarkus discovers beans defined in other modules, add the following to `application.properties` in the `app` module:

```properties
quarkus.index-dependency.service.group-id=com.example
quarkus.index-dependency.service.artifact-id=service
```

More details: https://www.baeldung.com/quarkus-bean-discovery-index

## Adding Extensions

Quarkus extensions must be added to the module where they are used.

```bash
./mvnw quarkus:add-extension -Dextensions=<name> -pl <module>
```

Example:

```bash
./mvnw quarkus:add-extension -Dextensions="resteasy-reactive" -pl service
```

## Running the Application

```bash
./mvnw compile quarkus:dev -pl app
```

```bash
./mvnw clean package -pl app -am
```

```bash
./mvnw clean install -Dnative -pl app -am
```

## Testing

```bash
./mvnw clean test
```

```bash
./mvnw test -pl service
```

## Best Practices

- Keep `core` free from framework dependencies.
- Use `core` to define service interfaces and `service` to implement them.
- Keep `app` as thin as possible — only responsible for wiring modules and running the application.

## Conclusion

This template provides a clean foundation for building scalable, testable, and modular Quarkus applications.

## License

This project is licensed under the [MIT License](LICENSE).
