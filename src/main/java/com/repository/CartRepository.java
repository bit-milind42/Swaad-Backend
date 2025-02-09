package com.repository;
import com.milind.model.Cart;
import com.milind.model.CartItem;
import com.request.AddCartItemRequest;

import org.springframework.data.jpa.repository.JpaRepository;

public class CartRepository extends JpaRepository<Cart,Long>{

    
}
