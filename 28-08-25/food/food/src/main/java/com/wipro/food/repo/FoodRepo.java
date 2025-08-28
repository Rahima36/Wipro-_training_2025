package com.wipro.food.repo;


import com.wipro.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepo extends JpaRepository<Food, Integer> {
    // Spring Data JPA provides methods like findAll(), findById(), save(), etc.
}