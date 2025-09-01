package com.wipro.order_service.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.order_service.model.CartItem;
import com.wipro.order_service.model.OrderEntity;
import com.wipro.order_service.repository.OrderRepository;
import com.wipro.order_service.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private final CartRepository cartRepo;
    private final OrderRepository orderRepo;

    public OrderService(CartRepository c, OrderRepository o) {
        this.cartRepo = c;
        this.orderRepo = o;
    }

    // Cart
    public CartItem addToCart(CartItem c) { return cartRepo.save(c); }
    public List<CartItem> viewCart(Long uid) { return cartRepo.findByUserId(uid); }
    public void removeCart(Long id) { cartRepo.deleteById(id); }

    // Order
    public OrderEntity createOrder(Long uid, List<Map<String,Object>> items) throws Exception {
        String json = new ObjectMapper().writeValueAsString(items);

        OrderEntity o = new OrderEntity(
                null,           // id will be auto-generated
                uid,
                "CREATED",
                LocalDateTime.now(),
                json
        );

        return orderRepo.save(o);
    }

    public List<OrderEntity> myOrders(Long uid) { return orderRepo.findByUserId(uid); }

    public OrderEntity cancelOrder(Long orderId) {
        OrderEntity order = orderRepo.findById(orderId).orElseThrow();
        order.setStatus("CANCELLED");
        return orderRepo.save(order);
    }
}