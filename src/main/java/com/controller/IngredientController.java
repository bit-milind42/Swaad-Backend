package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.milind.model.IngredientCategory;
import com.milind.model.IngredientsItem;
import com.request.IngredientCategoryRequest;
import com.request.IngredientRequest;
import com.service.IngredientsService;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {
    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(
        @RequestBody IngredientCategoryRequest req
    ) throws Exception {

        IngredientCategory item = ingredientsService.createIngredientCategory(req.getName(), req.getRestaurantId());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<?> createIngredientItem(@RequestBody IngredientRequest req) {
    System.out.println("Incoming ingredient request:");
    System.out.println("restaurantId = " + req.getRestaurantId());
    System.out.println("ingredientName = " + req.getIngredientName());
    System.out.println("categoryId = " + req.getCategoryId());

    try {
        IngredientsItem item = ingredientsService.createIngredientites(
            req.getRestaurantId(),
            req.getIngredientName(),
            req.getCategoryId()
        );
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }


    @PutMapping("/{id}/stoke")
    public ResponseEntity<IngredientsItem> UpdateIngredientStock(
        @PathVariable Long id
    ) throws Exception {

        IngredientsItem item = ingredientsService.updateStock(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> getRestaurantIngredient(
        @PathVariable Long id
    ) throws Exception {

        List<IngredientsItem> items = ingredientsService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
        @PathVariable Long id
    ) throws Exception {

        List<IngredientCategory> items = ingredientsService.findingredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

}
