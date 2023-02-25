package com.jean.todo.repositories;

import com.jean.todo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * A repository interface for managing User entities.
 *
 * Uses the JpaRepository interface to provide standard CRUD operations for User
 * entities.
 *
 * This interface is annotated with "@Repository" to indicate that it is a
 * Spring Data repository.
 *
 * The type parameters for JpaRepository are <User, String>, indicating that the
 * entity type is User and the ID type is String.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
  @Query("SELECT u FROM User u WHERE u.username = :username")
  User findByUsername(String username);
}
