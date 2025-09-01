package com.wipro.product_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.product_service.model.Product;
import com.wipro.product_service.service.ProductService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/product")
public class ProductController {
  private final ProductService svc;
  public ProductController(ProductService svc){ this.svc=svc; }

  @GetMapping public List<Product> list(){ return svc.all(); }
  @GetMapping("/{id}") public ResponseEntity<?> get(@PathVariable Long id){ return svc.get(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Product p, HttpServletRequest req) {
    Claims claims = (Claims) req.getAttribute("claims");
    if(claims==null || !"ADMIN".equals(claims.get("role"))) return ResponseEntity.status(403).body("Forbidden");
    return ResponseEntity.ok(svc.save(p));
  }

  @PutMapping
  public ResponseEntity<?> update(@RequestBody Product p, HttpServletRequest req) {
    Claims claims = (Claims) req.getAttribute("claims");
    if(claims==null || !"ADMIN".equals(claims.get("role"))) return ResponseEntity.status(403).body("Forbidden");
    return ResponseEntity.ok(svc.save(p));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id, HttpServletRequest req) {
    Claims claims = (Claims) req.getAttribute("claims");
    if(claims==null || !"ADMIN".equals(claims.get("role"))) return ResponseEntity.status(403).body("Forbidden");
    svc.delete(id);
    return ResponseEntity.ok("Deleted");
  }
}
