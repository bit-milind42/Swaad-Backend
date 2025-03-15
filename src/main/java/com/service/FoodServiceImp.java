// package com.service;

// import java.util.List;
// import java.util.Optional;
// import java.util.Locale.Category;
// import java.util.stream.Collector;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.milind.model.Food;
// import com.milind.model.Restaurant;
// import com.repository.FoodRepository;
// import com.request.CreateFoodRequest;

// @Service
// public class FoodServiceImp implements FoodService {
    
//     @Autowired
//     private FoodRepository foodRepository;

//     @Override 
//     public Food createFood(CreateFoodRequest req, Category category, Restaurant restarant) { 
//         Food food = new Food();
//         food.setFoodCategory(category); 
//         food.setRestaurant (restarant); 
//         food.setDescription(req.getDescription()); 
//         food.setImages(req.getImages()); 
//         food.setName(req.getName()); 
//         food.setPrice(req.getPrice()); 
//         food.setIngredients (req.getIngredients()); 
//         food.setSeasonal (req.isSeasional()); 
//         food.setVegetarian (req.isVegetarin());

//         Food savedFood= foodRepository.save(food); 
//         restarant.getFoods().add(savedFood);
//         return savedFood;
//     }
//     @Override 
//     public void deleteFood(Long foodId) throws Exception { 
//         Food food=findFoodById(foodId);
//         food.setRestaurant(null);
//         foodRepository.save(food);
//     }
//     @Override
//     public List<Food> getRestaurantsFood (Long restaurantId, 
//     boolean isVegitarain,
//      boolean isNonveg, 
//      boolean isSeasonal,
//      String foodCategory){
//         List<Food> foods= foodRepository.findByRestaurantId(restaurantId);
//         if(isVegitarain) { 
//             foods=filterByVegetarian (foods, isVegitarain); 
//             } 
//         if(isNonveg){ 
//             foods=filterByNonveg (foods, isNonveg); 
//             } 
//         if (isSeasonal) { 
//             foods=filterBySeasonal (foods, isSeasonal); 
//             } 
//         if(foodCategory!=null && !foodCategory.equals("")) { 
//             foods=filterByCategory (foods, foodCategory); 
//             }
//         return foods; 
//     }

//     private List<Food>filterByCategory(List<Food> foods,String foodCategory){
//         return foods.stream().filter(food -> {
//             if(food.getFoodCategory()!=null){
//                 return food.getFoodCategory().getName().equals(foodCategory);

//             }
//             return false;

//         }).collect(Collector.toList());
//     }

//     private List<Food>filterBySeasonal(List<Food> foods, boolean isSeasonal){
//         return foods.stream().filter(food -> food.isSeasonal()=isSeasional).collect(Collectors.toList());
        

//     }

//     private List<Food>filterByNonVeg(List<Food> foods,boolean isNonVeg){
//         return foods.stream().filter(food -> food.isVegetarian()==false).collect(Collectors.toList());
        

//     }


//     private List<Food>filterByVegetarian(List<Food> foods,boolean isVegitarain){
//         return foods.stream().filter(food -> food.isVegetarian()==isVegitarain).collect(Collectors.toList());
        

//     }

//     @Override 
//     public List<Food> searchFood (String keyword) { 
//         return foodRepository.searchFood(keyword); 
//     } 
    
//     @Override 
//     public Food findFoodById(Long foodId) throws Exception { 
    
//         Optional<Food> optionalFood = foodRepository.findById(foodId);
//         if(optionalFood.isEmpty()){
//             throw new Exception("Food not exist...");
//         }
//         return optionalFood.get();
//     } 
    
    
//     @Override 
//     public Food updateAvailibiityStatus (Long foodId) throws Exception { 
//         Food food=findFoodById(foodId);
//         food.setAvailable(!food.isAvailable());
//         return foodRepository.save(food);
//     }

// }








// package com.service;

// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.milind.model.Food;
// import com.milind.model.Restaurant;
// import com.milind.model.Category;
// import com.repository.FoodRepository;
// import com.request.CreateFoodRequest;

// @Service
// public class FoodServiceImp implements FoodService {
    
//     @Autowired
//     private FoodRepository foodRepository;

//     @Override 
//     public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) { 
//         Food food = new Food();
//         food.setFoodCategory(category); 
//         food.setRestaurant(restaurant); 
//         food.setDescription(req.getDescription()); 
//         food.setImages(req.getImages()); 
//         food.setName(req.getName()); 
//         food.setPrice(req.getPrice()); 
//         food.setIngredients(req.getIngredients()); 
//         food.setSeasonal(req.isSeasional()); 
//         food.setVegetarian(req.isVegetarin());

//         Food savedFood = foodRepository.save(food); 
//         restaurant.getFoods().add(savedFood);
//         return savedFood;
//     }

//     @Override 
//     public void deleteFood(Long foodId) throws Exception { 
//         Food food = findFoodById(foodId);
//         food.setRestaurant(null);
//         foodRepository.save(food);
//     }

//     @Override
//     public List<Food> getRestaurantsFood(Long restaurantId, 
//                                          boolean isVegetarian,
//                                          boolean isNonVeg, 
//                                          boolean isSeasonal,
//                                          String foodCategory) {
//         List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

//         if (isVegetarian) { 
//             foods = filterByVegetarian(foods, isVegetarian); 
//         } 
//         if (isNonVeg) { 
//             foods = filterByNonVeg(foods, isNonVeg); 
//         } 
//         if (isSeasonal) { 
//             foods = filterBySeasonal(foods, isSeasonal); 
//         } 
//         if (foodCategory != null && !foodCategory.isEmpty()) { 
//             foods = filterByCategory(foods, foodCategory); 
//         }

//         return foods; 
//     }

//     private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
//         return foods.stream()
//                     .filter(food -> food.getFoodCategory() != null && food.getFoodCategory().getName().equals(foodCategory))
//                     .collect(Collectors.toList());
//     }

//     private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
//         return foods.stream()
//                     .filter(food -> food.isSeasonal() == isSeasonal)
//                     .collect(Collectors.toList());
//     }

//     private List<Food> filterByNonVeg(List<Food> foods, boolean isNonVeg) {
//         return foods.stream()
//                     .filter(food -> !food.isVegetarian())
//                     .collect(Collectors.toList());
//     }

//     private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
//         return foods.stream()
//                     .filter(food -> food.isVegetarian() == isVegetarian)
//                     .collect(Collectors.toList());
//     }

//     @Override 
//     public List<Food> searchFood(String keyword) { 
//         return foodRepository.searchFood(keyword); 
//     } 
    
//     @Override 
//     public Food findFoodById(Long foodId) throws Exception { 
//         Optional<Food> optionalFood = foodRepository.findById(foodId);
//         if (optionalFood.isEmpty()) {
//             throw new Exception("Food not found...");
//         }
//         return optionalFood.get();
//     } 
    
//     @Override 
//     public Food updateAvailabilityStatus(Long foodId) throws Exception { 
//         Food food = findFoodById(foodId);
//         food.setAvailable(!food.isAvailable());
//         return foodRepository.save(food);
//     }

//     @Override
//     public Food createFood(CreateFoodRequest req, java.util.Locale.Category category, Restaurant restaurant) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'createFood'");
//     }
// }



// package com.service;

// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Component;
// import org.springframework.stereotype.Service;

// import com.milind.model.Food;
// import com.milind.model.Restaurant;
// import com.milind.model.Category;
// import com.repository.FoodRepository;
// import com.request.CreateFoodRequest;
// @Component
// @Service
// public class FoodServiceImp implements FoodService {
    
//     @Autowired
//     private FoodRepository foodRepository;

    
//     public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) { 
//         Food food = new Food();
//         food.setFoodCategory(category); 
//         food.setRestaurant(restaurant); 
//         food.setDescription(req.getDescription()); 
//         food.setImages(req.getImages()); 
//         food.setName(req.getName()); 
//         food.setPrice(req.getPrice()); 
//         food.setIngredients(req.getIngredients()); 
//         food.setSeasonal(req.isSeasional()); 
//         food.setVegetarian(req.isVegetarin());
//         return foodRepository.save(food); 
//     }

//     @Override 
//     public void deleteFood(Long foodId) throws Exception { 
//         Food food = findFoodById(foodId);
//         foodRepository.delete(food);
//     }

//     @Override
//     public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal, String foodCategory) {
//         List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
//         if (isVegetarian) foods = filterByVegetarian(foods);
//         if (isNonVeg) foods = filterByNonVeg(foods);
//         if (isSeasonal) foods = filterBySeasonal(foods);
//         if (foodCategory != null && !foodCategory.isEmpty()) foods = filterByCategory(foods, foodCategory);
//         return foods; 
//     }

//     private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
//         return foods.stream()
//                     .filter(food -> food.getFoodCategory() != null && food.getFoodCategory().getName().equals(foodCategory))
//                     .collect(Collectors.toList());
//     }

//     private List<Food> filterBySeasonal(List<Food> foods) {
//         return foods.stream().filter(Food::isSeasonal).collect(Collectors.toList());
//     }

//     private List<Food> filterByNonVeg(List<Food> foods) {
//         return foods.stream().filter(food -> !food.isVegetarian()).collect(Collectors.toList());
//     }

//     private List<Food> filterByVegetarian(List<Food> foods) {
//         return foods.stream().filter(Food::isVegetarian).collect(Collectors.toList());
//     }

//     @Override 
//     public List<Food> searchFood(String keyword) { 
//         return foodRepository.searchFood(keyword); 
//     } 
    
//     @Override 
//     public Food findFoodById(Long foodId) throws Exception { 
//         return foodRepository.findById(foodId)
//                 .orElseThrow(() -> new Exception("Food not found..."));
//     } 
    
//     @Override 
//     public Food updateAvailabilityStatus(Long foodId) throws Exception { 
//         Food food = findFoodById(foodId);
//         food.setAvailable(!food.isAvailable());
//         return foodRepository.save(food);
//     }

//     @Override
//     public Food createFood(CreateFoodRequest req, java.util.Locale.Category category, Restaurant restaurant) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'createFood'");
//     }
// }





// package com.service;

// import java.util.List;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.milind.model.Food;
// import com.milind.model.Restaurant;
// import com.milind.model.Category;
// import com.repository.FoodRepository;
// import com.request.CreateFoodRequest;

// @Service
// public class FoodServiceImp implements FoodService {
    
//     @Autowired
//     private FoodRepository foodRepository;

    
//     public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) { 
//         Food food = new Food();
//         food.setFoodCategory(category); 
//         food.setRestaurant(restaurant); 
//         food.setDescription(req.getDescription()); 
//         food.setImages(req.getImages()); 
//         food.setName(req.getName()); 
//         food.setPrice(req.getPrice()); 
//         food.setIngredients(req.getIngredients()); 
//         food.setSeasonal(req.isSeasional()); 
//         food.setVegetarian(req.isVegetarin());
        
//         return foodRepository.save(food); 
//     }

//     @Override 
//     public void deleteFood(Long foodId) { 
//         Food food = findFoodById(foodId);
//         foodRepository.delete(food);
//     }

//     @Override
//     public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal, String foodCategory) {
//         List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
        
//         if (isVegetarian) foods = filterByVegetarian(foods);
//         if (isNonVeg) foods = filterByNonVeg(foods);
//         if (isSeasonal) foods = filterBySeasonal(foods);
//         if (foodCategory != null && !foodCategory.isEmpty()) foods = filterByCategory(foods, foodCategory);
        
//         return foods; 
//     }

//     private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
//         return foods.stream()
//                     .filter(food -> food.getFoodCategory() != null && 
//                                     food.getFoodCategory().getName().equalsIgnoreCase(foodCategory))
//                     .collect(Collectors.toList());
//     }

//     private List<Food> filterBySeasonal(List<Food> foods) {
//         return foods.stream().filter(Food::isSeasonal).collect(Collectors.toList());
//     }

//     private List<Food> filterByNonVeg(List<Food> foods) {
//         return foods.stream().filter(food -> !food.isVegetarian()).collect(Collectors.toList());
//     }

//     private List<Food> filterByVegetarian(List<Food> foods) {
//         return foods.stream().filter(Food::isVegetarian).collect(Collectors.toList());
//     }

//     @Override 
//     public List<Food> searchFood(String keyword) { 
//         return foodRepository.searchFood(keyword); 
//     } 
    
//     @Override 
//     public Food findFoodById(Long foodId) { 
//         return foodRepository.findById(foodId)
//                 .orElseThrow(() -> new RuntimeException("Food not found with ID: " + foodId));
//     } 
    
//     @Override 
//     public Food updateAvailabilityStatus(Long foodId) { 
//         Food food = findFoodById(foodId);
//         food.setAvailable(!food.isAvailable());
//         return foodRepository.save(food);
//     }

//     @Override
//     public Food createFood(CreateFoodRequest req, java.util.Locale.Category category, Restaurant restaurant) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'createFood'");
//     }

   

    
// }





// package com.service;

// import java.util.List;
// import java.util.stream.Collectors;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import com.milind.model.Food;
// import com.milind.model.Restaurant;
// import com.milind.model.Category;
// import com.repository.FoodRepository;
// import com.request.CreateFoodRequest;

// @Service
// public class FoodServiceImp implements FoodService {
    
//     @Autowired
//     private FoodRepository foodRepository;

//     // @Override
//     // public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) { 
//     //     Food food = new Food();
//     //     food.setFoodCategory(category); 
//     //     food.setRestaurant(restaurant); 
//     //     food.setDescription(req.getDescription()); 
//     //     food.setImages(req.getImages()); 
//     //     food.setName(req.getName()); 
//     //     food.setPrice(req.getPrice()); 
//     //     food.setIngredients(req.getIngredients()); 
//     //     food.setSeasonal(req.isSeasional()); 
//     //     food.setVegetarian(req.isVegetarian());
//     //     return foodRepository.save(food); 
//     // }

//     @Override
//     public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) { 
//         Food food = new Food();
//         food.setFoodCategory(category); 
//         food.setRestaurant(restaurant); 
//         food.setDescription(req.getDescription()); 
//         food.setImages(req.getImages()); 
//         food.setName(req.getName()); 
//         food.setPrice(req.getPrice()); 
//         food.setIngredients(req.getIngredients()); 
//         food.setSeasonal(req.isSeasonal()); 
//         food.setVegetarian(req.isVegetarian());

//         return foodRepository.save(food); 
//     }

//     @Override 
//     public void deleteFood(Long foodId) throws Exception { 
//         Food food = findFoodById(foodId);
//         foodRepository.delete(food);
//     }

//     @Override
//     public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonVeg, boolean isSeasonal, String foodCategory) {
//         List<Food> foods = foodRepository.findByRestaurantId(restaurantId);
//         if (isVegetarian) foods = filterByVegetarian(foods);
//         if (isNonVeg) foods = filterByNonVeg(foods);
//         if (isSeasonal) foods = filterBySeasonal(foods);
//         if (foodCategory != null && !foodCategory.isEmpty()) foods = filterByCategory(foods, foodCategory);
//         return foods; 
//     }

//     private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
//         return foods.stream()
//                     .filter(food -> food.getFoodCategory() != null && food.getFoodCategory().getName().equalsIgnoreCase(foodCategory))
//                     .collect(Collectors.toList());
//     }

//     private List<Food> filterBySeasonal(List<Food> foods) {
//         return foods.stream().filter(Food::isSeasonal).collect(Collectors.toList());
//     }

//     private List<Food> filterByNonVeg(List<Food> foods) {
//         return foods.stream().filter(food -> !food.isVegetarian()).collect(Collectors.toList());
//     }

//     private List<Food> filterByVegetarian(List<Food> foods) {
//         return foods.stream().filter(Food::isVegetarian).collect(Collectors.toList());
//     }

//     @Override 
//     public List<Food> searchFood(String keyword) { 
//         return foodRepository.searchFood(keyword); 
//     } 
    
//     @Override 
//     public Food findFoodById(Long foodId) throws Exception { 
//         return foodRepository.findById(foodId)
//                 .orElseThrow(() -> new Exception("Food not found with ID: " + foodId));
//     } 
    
//     @Override 
//     public Food updateAvailabilityStatus(Long foodId) throws Exception { 
//         Food food = findFoodById(foodId);
//         food.setAvailable(!food.isAvailable());
//         return foodRepository.save(food);
//     }
// }




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
        food.setSeasonal(req.isSeasonal()); // Fixed the method name from isSeasional to isSeasonal
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