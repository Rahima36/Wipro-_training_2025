package com.wipro.order_service.controller;


import com.wipro.order_service.model.CartItem;
import com.wipro.order_service.model.OrderEntity;
import com.wipro.order_service.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService svc;

    public OrderController(OrderService svc) {
        this.svc = svc;
    }

    // Create a new order from the user's cart
    @PostMapping
    public ResponseEntity<OrderEntity> create(@RequestParam Long uid) throws Exception {
        List<CartItem> cart = svc.viewCart(uid);

        List<Map<String, Object>> items = cart.stream()
                .map(c -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("productId", c.getProductId());
                    map.put("quantity", c.getQuantity());
                    return map;
                })
                .collect(Collectors.toList());

        OrderEntity o = svc.createOrder(uid, items);
        return ResponseEntity.ok(o);
    }

    // Cancel an order by ID
    @PutMapping("/{id}")
    public ResponseEntity<OrderEntity> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(svc.cancelOrder(id));
    }

    // View orders of a user
    @GetMapping("/{uid}")
    public ResponseEntity<List<OrderEntity>> myOrders(@PathVariable Long uid) {
        return ResponseEntity.ok(svc.myOrders(uid));
    }
}