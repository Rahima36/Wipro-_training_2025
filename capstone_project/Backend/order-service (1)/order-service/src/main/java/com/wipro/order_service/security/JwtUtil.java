package com.wipro.order_service.security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
@Component
public class JwtUtil {
  @Value("${jwt.secret:change-this-demo-secret-change-this-demo-secret}")
  private String secret;
  @Value("${jwt.expMs:86400000}")
  private long expMs;
  private Key key(){ return Keys.hmacShaKeyFor(secret.getBytes()); }
  public String generate(String username, String role){
    return Jwts.builder().setSubject(username).claim("role", role)
      .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+expMs))
      .signWith(key(), SignatureAlgorithm.HS256).compact();
  }
  public Jws<Claims> parse(String token){
    return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token);
  }
}
