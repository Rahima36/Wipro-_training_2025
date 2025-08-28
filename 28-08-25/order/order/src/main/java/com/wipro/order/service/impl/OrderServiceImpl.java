package com.wipro.order.service.impl;

import com.wipro.order.dto.OrderRequest;
import com.wipro.order.entity.Order;
import com.wipro.order.repo.OrderRepo;
import com.wipro.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setUserId(orderRequest.getUserId());
        order.setFoodId(orderRequest.getFoodId());
        order.setQuantity(orderRequest.getQuantity());
        order.setTotalAmount(orderRequest.getTotalAmount());
        order.setOrderDate(new Date());

        return orderRepo.save(order);
    }

    @Override
    public List<Order> getOrderHistory(int userId) {
        return orderRepo.findByUserId(userId);
    }

    @Override
    public Order getOrderDetails(int orderId) {
        Optional<Order> orderOpt = orderRepo.findById(orderId);
        return orderOpt.orElse(null);
    }
}
