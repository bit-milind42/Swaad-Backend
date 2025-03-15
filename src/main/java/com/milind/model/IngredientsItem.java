// package com.milind.model;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.ManyToOne;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class IngredientsItem {
//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private Long id;

//     private String name;

//     @ManyToOne
//     private IngredientCategory category;

//     @JsonIgnore
//     @ManyToOne
//     private Restaurant restaurant;

//     private boolean inStoke=true;
// }





package com.milind.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientsItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Prefer IDENTITY for auto-incrementing
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false) // Ensuring correct foreign key
    private IngredientCategory category;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false) // Ensuring correct foreign key
    private Restaurant restaurant;

    private boolean inStock = true; // Fixed typo (was `inStoke`)
}
