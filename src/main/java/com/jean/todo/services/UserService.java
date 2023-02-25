package com.jean.todo.services;

import com.jean.todo.dtos.requests.NewRegisterRequest;
import com.jean.todo.entities.User;
import com.jean.todo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
  private final UserRepository userRepository;
  private final SecurityService securityService;

  public UserService(UserRepository userRepository,
                     SecurityService securityService) {
    this.userRepository = userRepository;
    this.securityService = securityService;
  }

  /**
   * Creates a new user.
   *
   * This method creates a new user from the provided request. It first
   * generates a salt, then hashes the user's password using the salt. The
   * hashed password and salt are then used to create a new user object.
   * Finally, the user is saved to the database.
   *
   * @param request The request containing the user's information.
   * @return The created user.
   */
  public User save(NewRegisterRequest request) {
    try {
      byte[] salt = securityService.generateSalt();
      byte[] password =
          securityService.hashingMethod(request.getPassword1(), salt);

      User createdUser = new User(request, password, salt);
      return userRepository.save(createdUser);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Validates a username.
   *
   * A username must be between 3 and 20 characters long, and can only contain
   * letters and numbers.
   *
   * @param username The username to validate.
   * @return True if the username is valid, false otherwise.
   */
  public boolean isValidUsername(String username) {
    return username.matches("[a-zA-Z0-9]{3,20}");
  }

  /**
   * Checks if a username is unique.
   *
   * A username is considered unique if it is not already in use by another
   * user. This method checks the database to see if a user with the provided
   * username exists.
   *
   * @param username The username to check.
   * @return True if the username is unique, false otherwise.
   */
  public boolean isUniqueUsername(String username) {
    return userRepository.findByUsername(username) == null;
  }

  /**
   * Validates a password.
   *
   * A password must be between 8 and 20 characters long, and can only Contain
   * letters and numbers.
   *
   * @param password The password to validate.
   * @return True if the password is valid, false otherwise.
   */
  public boolean isValiddPassword(String password) {
    // Password must be between 8 and 20 characters long
    return password.matches("[a-zA-Z0-9]{8,20}");
  }

  /**
   * Checks if two passwords match.
   *
   * @param password1 The first password.
   * @param password2 The second password.
   * @return True if the passwords match, false otherwise.
   */
  public boolean isMatchingPassword(String password1, String password2) {
    return password1.equals(password2);
  }
}
