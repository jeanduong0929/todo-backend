package com.jean.todo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * A data object representing a task.
 */
@Entity
@Table(name = "tasks")
public class Task {
  @Id
  private String id;
  @Column(name = "name", nullable = false)
  private String name;
  @Column(name = "completed", nullable = false)
  private boolean completed;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  @JsonBackReference
  private User user;

  public Task() {
  }

  public Task(String id, String name, boolean completed, User user) {
    this.id = id;
    this.name = name;
    this.completed = completed;
    this.user = user;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "Task{" +
        "id='" + id + '\'' +
        ", name='" + name + '\'' +
        ", completed=" + completed +
        '}';
  }
}
