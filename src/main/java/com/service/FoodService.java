
package com.service;

import java.util.List;
import com.milind.model.Food;
import com.milind.model.Restaurant;
import com.milind.model.Category;
import com.request.CreateFoodRequest;

public interface FoodService {
    Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);
    void deleteFood(Long foodId) throws Exception;
    List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal, String foodCategory);
    List<Food> searchFood(String keyword);
    Food findFoodById(Long foodId) throws Exception;
    Food updateAvailabilityStatus(Long foodId) throws Exception;
}