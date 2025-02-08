package com.service;

import java.util.List;
import java.util.Locale.Category;

import com.milind.model.Food;
import com.milind.model.Restaurant;
import com.request.CreateFoodRequest;

public interface FoodService {
    public  Food createFood(CreateFoodRequest req,Category category, Restaurant restaurant);

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantsFood(Long restaurnatId, 
    boolean isVegitarian, 
    boolean isNonveg,
    boolean isSeasonal, 
    String foodCategory
    );
    
    public List<Food> searchFood(String keyword);
    public Food findFoodById(Long foodId) throws Exception;
    public Food updateAvailabilityStatus(Long foodId) throws Exception;
} 
