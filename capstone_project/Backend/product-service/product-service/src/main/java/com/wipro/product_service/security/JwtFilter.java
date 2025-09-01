package com.wipro.product_service.security;



import com.wipro.product_service.security.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Get the "Authorization" header from the request.
        final String authHeader = request.getHeader("Authorization");
        String jwt = null;
        String username = null;

        // Check if the header is present and in the correct format (Bearer token).
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7); // Extract the token by skipping "Bearer "
            try {
                // Get the username from the token.
                Claims claims = jwtUtil.extractAllClaims(jwt);
                username = claims.getSubject();
            } catch (Exception e) {
                // If token extraction or validation fails, log the error and continue without setting authentication.
                logger.error("JWT token validation failed: " + e.getMessage());
            }
        }

        // Validate the token and check if the user is already authenticated.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                // If the token is valid, set the authentication in the SecurityContext.
                if (jwtUtil.validateToken(jwt)) {
                    // Create a UserDetails object from the username.
                    // Note: In a real application, you would load UserDetails from a database.
                    // For this example, we're creating a simple one.
                    UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                            username, "", new ArrayList<>()
                    );
                    
                    // Create an authentication token.
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    
                    // Set details for the authentication token.
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Set the authentication in the SecurityContext.
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (Exception e) {
                logger.error("Error setting authentication in SecurityContext: " + e.getMessage());
            }
        }

        // Pass the request to the next filter in the chain.
        filterChain.doFilter(request, response);
    }
}
