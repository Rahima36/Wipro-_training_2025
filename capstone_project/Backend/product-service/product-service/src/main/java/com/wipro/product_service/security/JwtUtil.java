package com.wipro.product_service.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // IMPORTANT: In a production application, this secret key should be
    // loaded from an environment variable or a configuration file.
    private final String SECRET_KEY = "your_secret_key_that_is_at_least_256_bits_long";

    /**
     * Extracts the username (subject) from a JWT token.
     * @param token The JWT token string.
     * @return The username.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date from a JWT token.
     * @param token The JWT token string.
     * @return The expiration date.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts a specific claim from a JWT token.
     * @param token The JWT token string.
     * @param claimsResolver A function to resolve the claim.
     * @param <T> The type of the claim.
     * @return The extracted claim.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from a JWT token.
     * @param token The JWT token string.
     * @return The claims object.
     */
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Generates a new JWT token.
     * @param claims Custom claims to include in the token.
     * @param subject The subject of the token (e.g., username).
     * @return The generated JWT token string.
     */
    public String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Overloaded method to generate a token with no custom claims.
     * @param subject The subject of the token (e.g., username).
     * @return The generated JWT token string.
     */
    public String generateToken(String subject) {
        return generateToken(new HashMap<>(), subject);
    }

    /**
     * Validates if a token is valid for a given user.
     * @param token The JWT token string.
     * @return true if the token is valid, false otherwise.
     */
    public Boolean validateToken(String token) {
        // Here we just check for expiration. In a real scenario, you'd also
        // check against a UserDetails object to confirm the username and other details.
        return !isTokenExpired(token);
    }

    /**
     * Checks if a token has expired.
     * @param token The JWT token string.
     * @return true if the token is expired, false otherwise.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Gets the signing key for the token.
     * @return The signing key.
     */
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
