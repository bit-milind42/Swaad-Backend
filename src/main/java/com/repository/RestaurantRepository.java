// package com.repository;

// import java.util.List;

// import org.springframework.data.jpa.repository.Query;

// import com.milind.model.Restaurant;

// public class RestaurantRepository {
    

//     @Query("SELECT r FROM Restaurant r WHERE Lower(r.name) LIKE Lower(concat('%',:query, '%')) " +
//             "OR Lower(r.cuisineType) LIKE lower(concat('%', :query, '%'))") 
//     List<Restaurant> findBySearchQuery(String query); 
    
//     Restaurant FindByOwnerId(Long userId);
// }


package com.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import com.milind.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("SELECT r FROM Restaurant r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(r.cuisineType) LIKE LOWER(CONCAT('%', :query, '%'))") 
    List<Restaurant> findBySearchQuery(@Param("query") String query); 

    // Restaurant findByOwnerId(Long owner);

    @Query("SELECT r FROM Restaurant r WHERE r.owner.id = :ownerId")
    Restaurant findByOwnerId(@Param("ownerId") Long ownerId);
}
