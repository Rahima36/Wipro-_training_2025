package com.wipro.food.service;


import com.wipro.food.entity.Food;

import java.util.List;

public interface FoodService {
 List<Food> findAll();
 Food findById(int id);
 void save(Food food);
 void deleteById(int id);
}
