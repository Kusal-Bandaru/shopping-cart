# shopping-cart
Shopping Cart Application on [Spring Boot](http://projects.spring.io/spring-boot/) with Hibernate/JPA

## Requirements

For building and running the application we need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on our local machine. One way is to execute the `main` method in the `com.shopping.cart.ShoppingCartApplication` class from the IDE.

Alternatively we can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```