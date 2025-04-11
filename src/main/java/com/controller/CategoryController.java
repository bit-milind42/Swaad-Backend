package com.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.milind.model.Category;
import com.milind.model.User;
import com.service.CategoryService;
import com.service.UserService;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;


    @PostMapping("/admin/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
    @RequestHeader("Authorization") String jwt)throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Category createdCategory=categoryService.createCategory(category.getName(), user.getId());
        
                return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
        
        
            }
        
            private Long getName() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getName'");
            }
        
            @GetMapping("/category/restaurant/{id}")
            public ResponseEntity<List<Category>> getRestaurantCategory(
                @PathVariable Long id,
                @RequestHeader("Authorization") String jwt)throws Exception{
                User user=userService.findUserByJwtToken(jwt);
                List<Category> Categories=categoryService.findCategoryByRestaurantId(id);

                return new ResponseEntity<>(Categories, HttpStatus.CREATED);

    }




}
