package com.wipro.order_service.controller;

import com.wipro.order_service.model.CartItem;
import com.wipro.order_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final OrderService svc;

    public CartController(OrderService svc) {
        this.svc = svc;
    }

    // Add product to cart
    @PostMapping("/addProd")
    public ResponseEntity<CartItem> add(@RequestBody CartItem c) {
        return ResponseEntity.ok(svc.addToCart(c));
    }

    // View cart by user
    @GetMapping("/{uid}")
    public ResponseEntity<List<CartItem>> view(@PathVariable Long uid) {
        return ResponseEntity.ok(svc.viewCart(uid));
    }

    // Delete cart item
    @DeleteMapping("/deleteProd/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.removeCart(id);
        return ResponseEntity.noContent().build();
    }
}