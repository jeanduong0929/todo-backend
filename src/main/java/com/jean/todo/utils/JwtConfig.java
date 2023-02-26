package com.jean.todo.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Properties;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {
  private final SignatureAlgorithm sigAlg = SignatureAlgorithm.HS256;
  private final Key signingKey;

  public JwtConfig() {
    Properties props = new Properties();
    try {
      props.load(getClass().getClassLoader().getResourceAsStream(
          "application.properties"));
    } catch (Exception e) {
      e.printStackTrace();
    }

    byte[] salt =
        DatatypeConverter.parseBase64Binary(props.getProperty("salt"));
    signingKey = new SecretKeySpec(salt, sigAlg.getJcaName());
  }

  public int getExpiration() { return 60 * 60 * 1000; }

  public SignatureAlgorithm getSigAlg() { return sigAlg; }

  public Key getSigningKey() { return signingKey; }
}
