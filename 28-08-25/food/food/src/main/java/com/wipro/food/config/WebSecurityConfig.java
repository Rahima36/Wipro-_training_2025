package com.wipro.food.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

 @Bean
 public UserDetailsService userDetailsService() {
     return username -> {
         // A dummy implementation for demonstration. 
         // In a real app, you would fetch the user from your database
         // and check if they exist.
         if ("test@example.com".equals(username)) {
             return User.withUsername("test@example.com")
                        .password("{noop}password") // {noop} is for plain text password
                        .authorities("ROLE_USER")
                        .build();
         }
         throw new UsernameNotFoundException("User not found");
     };
 }

 @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     // Create an instance of the JWTAuthorizationFilter
     JWTAuthorizationFilter jwtAuthorizationFilter = new JWTAuthorizationFilter(userDetailsService());

     // Configure the security filter chain
     http
         .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless API
         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
         .authorizeHttpRequests(authorize -> authorize
             // Public endpoints that don't require authentication
             .requestMatchers("/user/login", "/user").permitAll()
             // All other endpoints require authentication
             .anyRequest().authenticated()
         )
         .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class); // Add our custom filter

     return http.build();
 }
}
