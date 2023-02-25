package com.jean.todo.controllers;

import com.jean.todo.dtos.requests.NewRegisterRequest;
import com.jean.todo.services.UserService;
import com.jean.todo.utils.custom_exceptions.ResourceConflictException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Registers a new user.
   *
   * This method registers a new user. It first checks if the username is
   * valid, then checks if the username is unique. It then checks if the
   * password is valid, and finally checks if the passwords match. If all
   * checks pass, the user is saved to the database.
   *
   * @param request The request containing the user's information.
   * @return A response entity containing a message.
   */
  @PostMapping("/register")
  public ResponseEntity<?> register(@Validated
                                    @RequestBody NewRegisterRequest request) {
    if (!userService.isValidUsername(request.getUsername())) {
      throw new IllegalArgumentException(
          "Invalid username. Must be between 8 and 20 characters.");
    }

    if (!userService.isUniqueUsername(request.getUsername())) {
      throw new ResourceConflictException("Username already exists.");
    }

    if (!userService.isValiddPassword(request.getPassword1())) {
      throw new IllegalArgumentException(
          "Invalid password. Must be between 8 "
          + "and 20 characters, and contain at least one number, one "
          + "uppercase letter, and one lowercase letter");
    }

    if (!request.getPassword1().equals(request.getPassword2())) {
      throw new IllegalArgumentException("Passwords do not match.");
    }

    userService.save(request);
    return ResponseEntity.ok("User created successfully.");
  }

  /**
   * Handles IllegalArgumentExceptions.
   *
   * @param e The IllegalArgumentException.
   * @return A map containing a timestamp and a message.
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Map<String, Object>>
  handleIllegalArgumentException(IllegalArgumentException e) {
    Map<String, Object> response = new LinkedHashMap<>();
    response.put("timestamp", new Date(System.currentTimeMillis()));
    response.put("message", e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  /**
   * Handles ResourceConflictExceptions.
   *
   * @param e The ResourceConflictException.
   * @return A map containing a timestamp and a message.
   */
  @ExceptionHandler(ResourceConflictException.class)
  public ResponseEntity<Map<String, Object>>
  handleResourceConflictException(ResourceConflictException e) {
    Map<String, Object> response = new LinkedHashMap<>();
    response.put("timestamp", new Date(System.currentTimeMillis()));
    response.put("message", e.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
  }
}
