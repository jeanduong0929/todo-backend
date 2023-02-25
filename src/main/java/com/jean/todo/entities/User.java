package com.jean.todo.entities;

import com.jean.todo.dtos.requests.NewRegisterRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

/**
 * A data object representing a user.
 */
@Entity
@Table(name = "users")
public class User {
  @Id private String id;
  @Column(name = "username", nullable = false) private String username;
  @Column(name = "password", nullable = false) private byte[] password;
  @Column(name = "salt", nullable = false) private byte[] salt;

  public User() {}

  public User(NewRegisterRequest request, byte[] password, byte[] salt) {
    this.id = UUID.randomUUID().toString();
    this.username = request.getUsername();
    this.password = password;
    this.salt = salt;
  }

  public User(String id, String username, byte[] password, byte[] salt) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.salt = salt;
  }

  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public String getUsername() { return username; }

  public void setUsername(String username) { this.username = username; }

  public byte[] getPassword() { return password; }

  public void setPassword(byte[] password) { this.password = password; }

  public byte[] getSalt() { return salt; }

  public void setSalt(byte[] salt) { this.salt = salt; }

  @Override
  public String toString() {
    return "User{"
        + "id='" + id + '\'' + ", username='" + username + '\'' +
        ", password=" + password + ", salt=" + salt + '}';
  }
}
