package com.jean.todo.dtos.responses;

/**
 * The Principal class represents an authenticated user.
 */
public class Principal {
  private String id;
  private String username;
  private String token;

  public Principal() {}

  public Principal(String id, String username) {
    this.id = id;
    this.username = username;
  }

  public Principal(String id, String username, String token) {
    this.id = id;
    this.username = username;
    this.token = token;
  }

  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public String getUsername() { return username; }

  public void setUsername(String username) { this.username = username; }

  public String getToken() { return token; }

  public void setToken(String token) { this.token = token; }

  @Override
  public String toString() {
    return "Principal{"
        + "id='" + id + '\'' + ", username='" + username + '\'' + ", token='" +
        token + '\'' + '}';
  }
}
