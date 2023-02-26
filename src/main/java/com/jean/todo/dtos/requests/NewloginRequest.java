package com.jean.todo.dtos.requests;

/**
 * This class is used to create a new login request.
 */
public class NewloginRequest {
  private String username;
  private String password;

  public NewloginRequest() {}

  public NewloginRequest(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public String getUsername() { return username; }

  public void setUsername(String username) { this.username = username; }

  public String getPassword() { return password; }

  public void setPassword(String password) { this.password = password; }

  @Override
  public String toString() {
    return "NewloginRequest{"
        + "username='" + username + '\'' + ", password='" + password + '\'' +
        '}';
  }
}
