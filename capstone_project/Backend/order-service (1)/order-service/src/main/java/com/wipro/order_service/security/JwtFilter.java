package com.wipro.order_service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;
@Component
public class JwtFilter extends OncePerRequestFilter {
  private final JwtUtil jwt;
  public JwtFilter(JwtUtil jwt){ this.jwt = jwt; }
  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws ServletException, IOException {
    String h = req.getHeader("Authorization");
    if(h!=null && h.startsWith("Bearer ")){
      try{
        var claims = jwt.parse(h.substring(7)).getBody();
        var auth = new UsernamePasswordAuthenticationToken(
          claims.getSubject(), null, Collections.singleton(() -> "ROLE_"+claims.get("role", String.class)));
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
        SecurityContextHolder.getContext().setAuthentication(auth);
      }catch(Exception e){
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
        return;
      }
    }
    chain.doFilter(req,res);
  }
}