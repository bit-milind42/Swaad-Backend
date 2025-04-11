
package com.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.milind.model.Food;
import com.milind.model.Restaurant;
import com.milind.model.Category;
import com.repository.FoodRepository;
import com.request.CreateFoodRequest;
import java.util.Date;

@Service
public class FoodServiceImp implements FoodService {
    
    @Autowired
    private FoodRepository foodRepository;

    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) { 
        Food food = new Food();
        food.setFoodCategory(category); 
        food.setRestaurant(restaurant); 
        food.setDescription(req.getDescription()); 
        food.setImages(req.getImages()); 
        food.setName(req.getName()); 
        food.setPrice(req.getPrice()); 
        food.setIngredients(req.getIngredients());
        food.setAvailable(req.isAvailable());

        food.setSeasonal(req.isSeasonal()); // Fixed the method name from isSeasional to isSeasonal
        
        food.setCreationDate(new Date());
        food.setVegetarian(req.isVegetarian());

        return foodRepository.save(food); 
    }

    @Override 
    public void deleteFood(Long foodId) throws Exception { 
        Food food = findFoodById(foodId);
        foodRepository.delete(food);
    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal, String foodCategory) {
        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
        if (isVegetarian) foods = filterByVegetarian(foods);
        if (isNonVeg) foods = filterByNonVeg(foods);
        if (isSeasonal) foods = filterBySeasonal(foods);
        if (foodCategory != null && !foodCategory.isEmpty()) foods = filterByCategory(foods, foodCategory);
        return foods; 
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream()
                    .filter(food -> food.getFoodCategory() != null && food.getFoodCategory().getName().equalsIgnoreCase(foodCategory))
                    .collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods) {
        return foods.stream().filter(Food::isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVeg(List<Food> foods) {
        return foods.stream().filter(food -> !food.isVegetarian()).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods) {
        return foods.stream().filter(Food::isVegetarian).collect(Collectors.toList());
    }

    @Override 
    public List<Food> searchFood(String keyword) { 
        return foodRepository.searchFood(keyword); 
    } 
    
    @Override 
    public Food findFoodById(Long foodId) throws Exception { 
        return foodRepository.findById(foodId)
                .orElseThrow(() -> new Exception("Food not found with ID: " + foodId));
    } 
    
    @Override 
    public Food updateAvailabilityStatus(Long foodId) throws Exception { 
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);
    }
}