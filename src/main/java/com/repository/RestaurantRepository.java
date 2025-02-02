package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.milind.model.Restaurant;

public class RestaurantRepository {
    

    @Query("SELECT r FROM Restaurant r WHERE Lower(r.name) LIKE Lower(concat('%',:query, '%')) " +
            "OR Lower(r.cuisineType) LIKE lower(concat('%', :query, '%'))") 
    List<Restaurant> findBySearchQuery(String query); 
    
    Restaurant FindByOwnerId(Long userId);
}
