package com.wipro.order.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    
    private int foodId;
    
    private int quantity;
    
    private double totalAmount;
    
    private Date orderDate;

	public void setOrderDate(Date date) {
		// TODO Auto-generated method stub
		
	}

	public void setTotalAmount(Object totalAmount2) {
		// TODO Auto-generated method stub
		
	}

	public void setQuantity(Object quantity2) {
		// TODO Auto-generated method stub
		
	}

	public void setFoodId(Object foodId2) {
		// TODO Auto-generated method stub
		
	}

	public void setUserId(Object userId2) {
		// TODO Auto-generated method stub
		
	}
}