// package com.repository;

// import org.springframework.data.jpa.repository.JpaRepository;

// import com.milind.model.OrderItem;

// public class OrderItemRepository extends JpaRepository <OrderItem,Long>{
    
// }
 

package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.milind.model.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
