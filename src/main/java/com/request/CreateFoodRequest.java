// package com.request;

// import java.util.List;
// import java.util.Locale.Category;

// import com.milind.model.IngredientsItem;

// import lombok.Data;


// @Data

// public class CreateFoodRequest {
//     private String name;
//     private String description;
//     private Long price;

//     private Category category;
//     private List<String> images;

//     private Long restaurantId;
//     private boolean vegetarin;
//     private boolean seasional;
//     private List<IngredientsItem> ingredients;
// }



package com.request;

import java.util.List;

import com.milind.model.Category;  // Correct import
import com.milind.model.IngredientsItem;

import lombok.Data;

@Data
public class CreateFoodRequest {
    private String name;
    private String description;
    private Long price;

    private Category category;  // Correct type
    private List<String> images;

    private Long restaurantId;
    private boolean vegetarian;  // Fixed spelling
    private boolean seasonal;    // Fixed spelling
    private List<IngredientsItem> ingredients;
}
