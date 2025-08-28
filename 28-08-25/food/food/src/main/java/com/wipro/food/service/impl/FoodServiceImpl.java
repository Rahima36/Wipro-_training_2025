package com.wipro.food.service.impl;

import com.wipro.food.entity.Food;
import com.wipro.food.repo.FoodRepo;
import com.wipro.food.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {

 @Autowired
 FoodRepo foodRepo;

 @Override
 public List<Food> findAll() {
     return foodRepo.findAll();
 }

 @Override
 public Food findById(int id) {
     Optional<Food> foodOpt = foodRepo.findById(id);
     return foodOpt.orElse(null);
 }

 @Override
 public void save(Food food) {
     foodRepo.save(food);
 }

 @Override
 public void deleteById(int id) {
     foodRepo.deleteById(id);
 }
}
