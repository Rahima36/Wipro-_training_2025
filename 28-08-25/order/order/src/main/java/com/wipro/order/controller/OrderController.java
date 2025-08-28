package com.wipro.order.controller;

import com.wipro.order.dto.OrderRequest;
import com.wipro.order.entity.Order;
import com.wipro.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public Order placeOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest);
    }

    @GetMapping("/history/{userId}")
    public List<Order> getOrderHistory(@PathVariable int userId) {
        return orderService.getOrderHistory(userId);
    }

    @GetMapping("/{orderId}")
    public Order getOrderDetails(@PathVariable int orderId) {
        return orderService.getOrderDetails(orderId);
    }
}
