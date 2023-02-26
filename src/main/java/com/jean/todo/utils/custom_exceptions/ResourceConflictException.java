package com.jean.todo.utils.custom_exceptions;

/**
 * A custom exception to be thrown when a resource conflict occurs.
 *
 */
public class ResourceConflictException extends RuntimeException {
  public ResourceConflictException(String message) {
    super(message);
  }
}
