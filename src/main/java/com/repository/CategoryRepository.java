// package com.repository;

// import java.util.List;
// import java.util.Locale.Category;

// import org.springframework.data.jpa.repository.JpaRepository;

// public class CategoryRepository extends JpaRepository<Category, Long> {
//     public List<Category> findByRestaurantId(Long id);
// }

package com.repository;

import com.milind.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByRestaurantId(Long id);
}
