package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.milind.model.Food;
import com.milind.model.Restaurant;
import com.milind.model.User;
import com.response.CreateFoodRequest;
import com.response.MessageResponse;
import com.service.FoodService;
import com.service.RestaurantService;
import com.service.UserService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    
    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping 
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest req, 
    @RequestHeader("Authorization") String jwt) throws Exception {

    User user =userService.findUserByJwtToken(jwt); 
    Restaurant restaurant=restaurantService.findRestaurantById(req.getRestaurantId()); 
    Food food=foodService.createFood(req, req.getCategory(), restaurant); 
    return new ResponseEntity<> (food, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
     @RequestHeader ("Authorization") String jwt) throws Exception{ 

    User user =userService.findUserByJwtToken(jwt); 
    foodService.deleteFood(id);
     
    MessageResponse res=new MessageResponse();
    res.setMessage("Food deleted successfully");
    return new ResponseEntity<> (res, HttpStatus.CREATED);

    }


    @PutMapping ("/{id}")
    public ResponseEntity<Food> updateFoodAvaibilityStatus(@PathVariable Long id,
     @RequestHeader ("Authorization") String jwt) throws Exception{ 

    User user =userService.findUserByJwtToken(jwt); 
    Food food=foodService.updateFoodAvaibilityStatus(id);
     
    
    return new ResponseEntity<> (food, HttpStatus.CREATED);

    }

}
