package com.wipro.order_service.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
@Builder
@Entity
public class CartItem {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private Long userId;
  private Long productId;
  private Integer quantity;
  public Long getId() {
	return id;
  }
  public void setId(Long id) {
	this.id = id;
  }
  public Long getUserId() {
	return userId;
  }
  public void setUserId(Long userId) {
	this.userId = userId;
  }
  public Long getProductId() {
	return productId;
  }
  public void setProductId(Long productId) {
	this.productId = productId;
  }
  public Integer getQuantity() {
	return quantity;
  }
  public void setQuantity(Integer quantity) {
	this.quantity = quantity;
  }
  @Override
  public String toString() {
	return "CartItem [id=" + id + ", userId=" + userId + ", productId=" + productId + ", quantity=" + quantity + "]";
  }
  public CartItem(Long id, Long userId, Long productId, Integer quantity) {
	super();
	this.id = id;
	this.userId = userId;
	this.productId = productId;
	this.quantity = quantity;
  }
  public CartItem() {
	super();
	// TODO Auto-generated constructor stub
  }
  
  
}