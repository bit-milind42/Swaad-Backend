// package com.service;

// import java.util.List;
// import java.util.Optional;
// import java.util.Locale.Category;

// import org.springframework.beans.factory.annotation.Autowired;

// import com.milind.model.Restaurant;
// import com.repository.CategoryRepository;

// public class CategoryServiceImp implements CategoryService {
//     @Autowired
//     private RestaurantService restaurantService;

//     @Autowired
//     private CategoryRepository categoryRepository;

//     @Override
//     public Category createCategory(String name, Long userId) throws Exception {
//         Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
//         Category category=new Category();
//         category.setName(name);
//         category.setRestaurant(restaurant);

//         return categoryRepository.save(category);
//     }


    
//     @Override 
//     public List<Category> findCategoryByRestaurantId(Long id) throws Exception { 
//         Restaurant restaurant=restaurantService.getRestaurantByUserId(id);
//         return categoryRepository.findByRestaurantId(restaurant.getId()); 
//     } 
    
//     @Override 
//     public Category findCategoryById(Long id) throws Exception { 
//         Optional<Category> optionalCategory=categoryRepository.findById(id);

//         if(optionalCategory.isEmpty()){
//             throw new Exception("Category not found");
//         }
//         return optionalCategory.get();
//     }
    
// }






package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milind.model.Category;
import com.milind.model.Restaurant;
import com.repository.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);

        return categoryRepository.save(category);
    }

    @Override 
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception { 
        Restaurant restaurant = restaurantService.getRestaurantByUserId(id);
        return categoryRepository.findByRestaurantId(restaurant.getId()); 
    } 
    
    @Override 
    public Category findCategoryById(Long id) throws Exception { 
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isEmpty()) {
            throw new Exception("Category not found");
        }
        return optionalCategory.get();
    }

   
    public java.util.Locale.Category createCategory(java.util.Locale.Category category, Long name, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCategory'");
    }

    @Override
    public Category createCategory(Category category, Long name, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createCategory'");
    }
}
