package com.jean.todo.services;

import com.jean.todo.dtos.responses.Principal;
import com.jean.todo.utils.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import java.sql.Date;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
  private final JwtConfig jwtConfig;

  public TokenService(JwtConfig jwtConfig) {
    this.jwtConfig = jwtConfig;
  }

  public String generateToken(Principal subject) {
    long now = System.currentTimeMillis();
    JwtBuilder tokenBuilder = Jwts.builder()
        .setId(subject.getId())
        .setIssuer("artifactId")
        .setIssuedAt(new Date(now))
        .setExpiration(new Date(now + jwtConfig.getExpiration()))
        .setSubject(subject.getUsername())
        .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());
    return tokenBuilder.compact();
  }

  public Principal extractRequesterDetails(String token) {
    try {
      Claims claims = Jwts.parser()
          .setSigningKey(jwtConfig.getSigningKey())
          .parseClaimsJws(token)
          .getBody();

      return new Principal(claims.getId(), claims.getSubject());
    } catch (Exception e) {
      return null;
    }
  }
}
