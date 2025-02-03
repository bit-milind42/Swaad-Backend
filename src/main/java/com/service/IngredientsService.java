package com.service;

import java.util.List;

import com.milind.model.IngredientCategory;
import com.milind.model.IngredientsItem;

public interface IngredientsService {
    
     
    public IngredientCategory createIngredientCategory(String name, Long restaurantId)throws Exception; 

    public IngredientCategory findingredientCategoryById(Long id)throws Exception; 

    public List<IngredientCategory> findingredientCategoryByRestaurantId(Long id) throws Exception; 

    public IngredientsItem createIngredientites (Long restaurantid, String ingredientName, Long categoryId) throws Exception;

    public List<IngredientsItem> findRestaurantsIngredients (Long restaurantId); 

    public IngredientsItem updateStock (Long id) throws Exception;

}
