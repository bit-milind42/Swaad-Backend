// package com.service;

// import java.util.List;
// import java.util.Locale.Category;

// import org.springframework.stereotype.Component;
// import org.springframework.stereotype.Service;

// import com.milind.model.Food;
// import com.milind.model.Restaurant;
// import com.request.CreateFoodRequest;

// @Service
// @Component
// public interface FoodService {
//     public  Food createFood(CreateFoodRequest req,Category category, Restaurant restaurant);

//     void deleteFood(Long foodId) throws Exception;

//     public List<Food> getRestaurantsFood(Long restaurnatId, 
//     boolean isVegitarian, 
//     boolean isNonveg,
//     boolean isSeasonal, 
//     String foodCategory
//     );
    
//     public List<Food> searchFood(String keyword);
//     public Food findFoodById(Long foodId) throws Exception;
//     public Food updateAvailabilityStatus(Long foodId) throws Exception;
// } 



// package com.service;

// import java.util.List;
// import java.util.Locale.Category;

// import com.milind.model.Food;
// import com.milind.model.Restaurant;
// import com.request.CreateFoodRequest;


// public interface FoodService {
//     public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

//     void deleteFood(Long foodId) throws Exception;

//     public List<Food> getRestaurantsFood(Long restaurantId, 
//     boolean isVegitarian, 
//     boolean isNonveg,
//     boolean isSeasonal, 
//     String foodCategory
//     );
    
//     public List<Food> searchFood(String keyword);
//     public Food findFoodById(Long foodId) throws Exception;
//     public Food updateAvailabilityStatus(Long foodId) throws Exception;
// }




// package com.service;

// import java.util.List;
// import com.milind.model.Food;
// import com.milind.model.Restaurant;
// import com.milind.model.Category;
// import com.request.CreateFoodRequest;

// public interface FoodService {
//     public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);
//     void deleteFood(Long foodId) throws Exception;
//     public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal, String foodCategory);
//     public List<Food> searchFood(String keyword);
//     public Food findFoodById(Long foodId) throws Exception;
//     public Food updateAvailabilityStatus(Long foodId) throws Exception;
// }





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