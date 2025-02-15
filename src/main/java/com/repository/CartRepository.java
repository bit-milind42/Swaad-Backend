// package com.repository;
// import com.milind.model.Cart;
// import com.milind.model.CartItem;
// import com.request.AddCartItemRequest;

// import org.springframework.data.jpa.repository.JpaRepository;

// public class CartRepository extends JpaRepository<Cart,Long>{

    
// }


package com.repository;

import com.milind.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
