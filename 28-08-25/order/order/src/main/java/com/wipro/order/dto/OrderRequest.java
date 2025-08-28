package com.wipro.order.dto;

import lombok.Data;

@Data
public class OrderRequest{
    private int userId;
    private int foodId;
    private int quantity;
    private double totalAmount;
    
	public Object getTotalAmount() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getQuantity() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getFoodId() {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getUserId() {
		// TODO Auto-generated method stub
		return null;
	}
	}