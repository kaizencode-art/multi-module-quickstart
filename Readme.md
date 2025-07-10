# Quarkus Multi-Module Quickstart

This project is a modular Quarkus starter that separates application concerns across multiple Maven modules. It follows clean architecture principles and supports scalable, maintainable development.

## Project Structure

```
multi-module-quickstart/
├── app/         # Application entrypoint, Quarkus runner
├── core/        # Domain models, interfaces, pure logic
├── service/     # Quarkus-aware service implementations
├── pom.xml      # Parent POM with dependency and plugin management
├── README.md
└── LICENSE
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
Quarkus performs bean discovery at build time to minimize startup cost. In multi-module projects, it only indexes beans from the main module and its direct dependencies.
There are two supported strategies to ensure Quarkus correctly discovers beans defined in other modules:
### 1. Configure Indexing in `application.properties`
In the `app` module, explicitly include additional modules in bean indexing:
```properties
quarkus.index-dependency.service.group-id=com.example
quarkus.index-dependency.service.artifact-id=service
```
This instructs Quarkus to scan the `service` module for CDI beans.
### 2. Use the Jandex Maven Plugin
Alternatively, use the `jandex-maven-plugin` to pre-index classes in modules providing beans.
In the **parent** `pom.xml`, declare the plugin in `pluginManagement`:
```xml
<build>
  <pluginManagement>
    <plugins>
      <plugin>
        <groupId>io.smallrye</groupId>
        <artifactId>jandex-maven-plugin</artifactId>
        <version>${jandex-maven-plugin.version}</version>
        <executions>
          <execution>
            <id>make-index</id>
            <goals>
              <goal>jandex</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </pluginManagement>
</build>
```
In any **child module** that declares CDI beans (e.g., `service`), activate the plugin:
```xml
<build>
  <plugins>
    <plugin>
      <groupId>io.smallrye</groupId>
      <artifactId>jandex-maven-plugin</artifactId>
    </plugin>
  </plugins>
</build>
```
This setup enables Quarkus to discover and index CDI beans in non-application modules without requiring property declarations.

More details: https://www.baeldung.com/quarkus-bean-discovery-index  

Note: `beans.xml` is intentionally omitted, as Quarkus no longer requires it for CDI discovery; relying on it is considered a legacy workaround, not aligned with Quarkus's build-time model.

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
./mvnw compile quarkus:dev -pl hello
```

```bash
./mvnw clean package -pl hello -am
```

```bash
./mvnw clean install -Dnative -pl hello -am
```

## Testing

```bash
./mvnw clean test
```

```bash
./mvnw test -pl service
```

## Conclusion

This template provides a clean foundation for building scalable, testable, and modular Quarkus applications.

## License

This project is licensed under the [MIT License](LICENSE).
