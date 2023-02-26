package com.jean.todo.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
  /**
   * Generates a salt key.
   *
   * This method generates a salt key using the SecureRandom class. The salt
   * key is used to encrypt a password.
   *
   * @return The salt key.
   */
  public byte[] generateSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    return salt;
  }

  /**
   * Hashes a password.
   *
   * This method uses the SHA-512 hashing algorithm to hash a password. The
   * salt key is used to encrypt the password.
   *
   * @param password The password to hash.
   * @param salt     The salt key to use.
   * @return The hashed password.
   * @throws NoSuchAlgorithmException If the SHA-512 algorithm is not available.
   */
  public byte[] hashingMethod(String password, byte[] salt) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-512");
    md.update(salt);
    return md.digest(password.getBytes(StandardCharsets.UTF_8));
  }

  /**
   * Compares two byte arrays to determine if they match.
   *
   * @param actualPassword   The actual password entered by the user.
   * @param expectedPassword The expected password stored in a database or other
   *                         secure storage system.
   * @return Returns true if the two byte arrays match, or false if they don't.
   */
  public boolean isMatchingPassword(byte[] actualPassword, byte[] expectedPassword) {
    return MessageDigest.isEqual(actualPassword, expectedPassword);
  }
}
