package com.jean.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class for the application.
 *
 * This class is annotated with "@SpringBootApplication" to indicate that it is
 * a Spring Boot application.
 *
 * The main method of this class is used to start the application.
 */
@SpringBootApplication
public class TodoApplication {

  public static void main(String[] args) {
    SpringApplication.run(TodoApplication.class, args);
  }
}
