# How To Start Zephyr-Aire

Gradle 6, Java 11, Maven 3.6.2

`mvn clean install` in top-level repo

`sass:watch` in aire-core

`mvn spring-boot:run` in aire-core

Go to `http://localhost:8080/` to view

To install a plugin copy its jar to `aire-core/target/kernel/deployments`




## Running in IntelliJ

Add `compiler.automake.allow.when.app.running` in the Registry Action

In Preferences, check all of the compiler options (if currently unchecked)

Refresh the maven project, if need be

Run `Application.java` -- doing `sass:watch` is still required

Go to `http://localhost:8080/` to view