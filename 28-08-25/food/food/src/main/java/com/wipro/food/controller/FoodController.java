package com.wipro.food.controller;


import com.wipro.food.entity.Food;
import com.wipro.food.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {

 @Autowired
 FoodService foodService;

 @GetMapping("/list")
 public List<Food> getAllFoodItems() {
     return foodService.findAll();
 }

 @GetMapping("/{id}")
 public Food getFoodItemById(@PathVariable int id) {
     return foodService.findById(id);
 }

 @PostMapping("/add")
 public void addFoodItem(@RequestBody Food food) {
     foodService.save(food);
 }

 @DeleteMapping("/{id}")
 public void deleteFoodItem(@PathVariable int id) {
     foodService.deleteById(id);
 }
}
