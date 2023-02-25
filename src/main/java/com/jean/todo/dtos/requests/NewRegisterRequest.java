package com.jean.todo.dtos.requests;

/**
 * A data object representing a new user registration request.
 *
 * The class has three properties: a username, and two password fields (one for
 * entering the password, and one for confirming it). The class provides getters
 * and setters for these fields, and a constructor to create a new request with
 * the provided values. It also overrides the `toString()` method to provide a
 * string representation of the request.
 *
 * This class is typically used to receive and validate new user registration
 * requests from clients, and can be passed to a service or DAO layer for
 * further processing.
 */
public class NewRegisterRequest {
  private String username;
  private String password1;
  private String password2;

  public NewRegisterRequest() {}

  public NewRegisterRequest(String username, String password1,
                            String password2) {
    this.username = username;
    this.password1 = password1;
    this.password2 = password2;
  }

  public String getUsername() { return username; }

  public void setUsername(String username) { this.username = username; }

  public String getPassword1() { return password1; }

  public void setPassword1(String password1) { this.password1 = password1; }

  public String getPassword2() { return password2; }

  public void setPassword2(String password2) { this.password2 = password2; }

  @Override
  public String toString() {
    return "NewRegisterRequest{"
        + "username='" + username + '\'' + ", password1='" + password1 + '\'' +
        ", password2='" + password2 + '\'' + '}';
  }
}
