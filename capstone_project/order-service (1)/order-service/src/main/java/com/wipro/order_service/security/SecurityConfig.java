package com.wipro.order_service.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration @EnableMethodSecurity
public class SecurityConfig {
  private final JwtFilter jwt; public SecurityConfig(JwtFilter jwt){ this.jwt=jwt; }
  @Bean public SecurityFilterChain filter(HttpSecurity http) throws Exception {
    http.csrf(cs->cs.disable())
      .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(a->a.anyRequest().authenticated())
      .addFilterBefore(jwt, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
