package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milind.model.IngredientCategory;
import com.milind.model.IngredientsItem;
import com.milind.model.Restaurant;
import com.repository.IngredientCategoryRepository;
import com.repository.IngredientItemRepository;

@Service
public class IngredientServiceImp implements IngredientsService {

    @Autowired
    private IngredientItemRepository ingredientItemRepository;

    @Autowired
    private IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        IngredientCategory category = new IngredientCategory();
        category.setRestaurant(restaurant);
        category.setName(name);

        return ingredientCategoryRepository.save(category);
    }

    
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception  {
        Optional<IngredientCategory> opt = ingredientCategoryRepository.findById(id);

        if (opt.isEmpty()) {
            throw new Exception("Ingredient category not found");
        }
        return opt.get();
    }

   
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long restaurantId) throws Exception {
        restaurantService.findRestaurantById(restaurantId);
        return ingredientCategoryRepository.findByRestaurantId(restaurantId);
    }
   

    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        try {
            // Log inputs for debugging
            System.out.println("Creating Ingredient:");
            System.out.println("restaurantId = " + restaurantId);
            System.out.println("ingredientName = " + ingredientName);
            System.out.println("categoryId = " + categoryId);
    
            // Fetch associated entities
            Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
            if (restaurant == null) {
                throw new Exception("Restaurant not found with ID: " + restaurantId);
            }
            System.out.println("Fetched restaurant: " + restaurant);
    
            IngredientCategory category = findIngredientCategoryById(categoryId);
            if (category == null) {
                throw new Exception("Ingredient category not found with ID: " + categoryId);
            }
            System.out.println("Fetched category: " + category);
    
            // Create and assign values to the new ingredient
            IngredientsItem item = new IngredientsItem();
            item.setName(ingredientName);
            item.setRestaurant(restaurant);
            item.setCategory(category);
    
            // Save to DB
            IngredientsItem saved = ingredientItemRepository.save(item);
            System.out.println("Saved ingredient: " + saved);
    
            // Optional: update category's ingredient list in memory
            if (category.getIngredients() == null) {
                category.setIngredients(new ArrayList<>());
            }
            category.getIngredients().add(saved); // only updates in-memory unless you re-save the category
    
            return saved;
        } catch (Exception e) {
            System.err.println("Error creating ingredient:");
            e.printStackTrace(); // shows the stack trace in logs
            throw new Exception("Failed to create ingredient: " + e.getMessage());
        }
    }
    
    
    

    @Override
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {
        return ingredientItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> optionalIngredientsItem = ingredientItemRepository.findById(id);
        if (optionalIngredientsItem.isEmpty()) {
            throw new Exception("Ingredient not found");
        }
        IngredientsItem ingredientsItem = optionalIngredientsItem.get();
        ingredientsItem.setInStock(!ingredientsItem.isInStock());
        return ingredientItemRepository.save(ingredientsItem);
    }

    @Override
    public IngredientCategory findingredientCategoryById(Long id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findingredientCategoryById'");
    }

    @Override
    public List<IngredientCategory> findingredientCategoryByRestaurantId(Long restaurantId) throws Exception {
        // Optional: validate restaurant exists
        restaurantService.findRestaurantById(restaurantId);

        // Fetch categories belonging to the restaurant
        return ingredientCategoryRepository.findByRestaurantId(restaurantId);
    }


    @Override
    public IngredientsItem createIngredientites(Long restaurantId, String ingredientName, Long categoryId)
            throws Exception {
        return createIngredientItem(restaurantId, ingredientName, categoryId);
    }

}

