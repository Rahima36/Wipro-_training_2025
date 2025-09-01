package com.wipro.order_service.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String status;
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TEXT")
    private String itemsJson;

    // ✅ Empty constructor (required by JPA)
    public OrderEntity() {}

    // ✅ Full constructor
    public OrderEntity(Long id, Long userId, String status, LocalDateTime createdAt, String itemsJson) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
        this.itemsJson = itemsJson;
    }

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getItemsJson() { return itemsJson; }
    public void setItemsJson(String itemsJson) { this.itemsJson = itemsJson; }
}