// package com.service;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.milind.model.IngredientCategory;
// import com.milind.model.IngredientsItem;
// import com.milind.model.Restaurant;
// import com.repository.IngredientCategoryRepository;

// @Service
// public class IngredientServiceImp implements IngredientsService{

//     @Autowired
//     private IngredientsItemRepository ingredientsItemRepository;

//     @Autowired
//     private IngredientCategoryRepository ingredientCategoryRepositoryngredientCategoryRepository;

//     @Autowired
//     private RestaurantService restaurantService;

//     @Override
//     public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception{
//         Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

//         IngredientCategory category=new IngredientCategory();
//         category.setRestaurant(restaurant);
//         category.setName(name);

//         return ingredientCategoryRepository.save(category);
//     }

    

//     @Override
//     public IngredientCategory findIngredientCategoryById(Long id) throws Exception{
//         Optional<IngredientCategory> opt= ingredientCategoryRepository.findById(id);

//         if(opt.isEmpty()){
//             throw new Exception("ingredient category not fount");
//         }
//         return opt.get();
//     }

//     @Override
//     public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception{
//         restaurantService.findRestaurantById(id);
//         return ingredientCategoryRepository.findByRestaurantId(id);
//     }

//     @Override
    
//     public IngredientCategory createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception{
//         Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
//         IngredientCategory category=findIngredientCategoryById(categoryId);

//         IngredientsItem item=new IngredientsItem();
//         item.setName(ingredientName);
//         item.setRestaurant(restaurant);
//         item.setCategory(category);

//         IngredientsItem ingredient = ingredientsItemRepository.save(item);
//         category.getIngredients().add(ingredient);


//         return ingredient;
//     }

//     @Override
//     public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {
//         return ingredientsItemRepository.findByRestaurantId(restaurantId);
//     }

//     @Override
//     public IngredientsItem updateStock(Long id) throws Exception {
//         Optional<IngredientsItem> optionalIngredientsItem = ingredientItemRepository.findById(id);
//         if(optionalIngredientsItem.isEmpty()){
//             throw new Exception("ingredient not fouund");
//         }
//         IngredientsItem ingredientsItem=optionalIngredientsItem.get();
//         ingredientsItem.setInStoke(!ingredientsItem.isInStoke());
//         return ingredientsItemRepository.save(ingredientsItem);
//     }



    
// }






package com.service;

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
    private IngredientItemRepository ingredientsItemRepository;

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

   
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepository.findByRestaurantId(id);
    }

   
    public IngredientsItem createIngredientItem(Long restaurantId, String ingredientName, Long categoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = findIngredientCategoryById(categoryId);

        IngredientsItem item = new IngredientsItem();
        item.setName(ingredientName);
        item.setRestaurant(restaurant);
        item.setCategory(category);

        IngredientsItem ingredient = ingredientsItemRepository.save(item);
        category.getIngredients().add(ingredient);

        return ingredient;
    }

    @Override
    public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {
        return ingredientsItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> optionalIngredientsItem = ingredientsItemRepository.findById(id);
        if (optionalIngredientsItem.isEmpty()) {
            throw new Exception("Ingredient not found");
        }
        IngredientsItem ingredientsItem = optionalIngredientsItem.get();
        ingredientsItem.setInStock(!ingredientsItem.isInStock());
        return ingredientsItemRepository.save(ingredientsItem);
    }

    @Override
    public IngredientCategory findingredientCategoryById(Long id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findingredientCategoryById'");
    }

    @Override
    public List<IngredientCategory> findingredientCategoryByRestaurantId(Long id) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findingredientCategoryByRestaurantId'");
    }

    @Override
    public IngredientsItem createIngredientites(Long restaurantid, String ingredientName, Long categoryId)
            throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createIngredientites'");
    }
}

