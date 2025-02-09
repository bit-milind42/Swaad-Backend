package com.request;

import java.util.List;

import com.milind.model.Cart;
import com.milind.model.CartItem;

import lombok.Data;

@Data
public class AddCartItemRequest {

    private Long foodId;
    private int quanity;
    private List<String> ingredients;
    
    // public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception;

    // public CartItem updateCartItemQuality(Long cartItemId,int quanity) throws Exception;

    // public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;

    // public Long calculateCartTotals(Cart cart) throws Exception;

    // public Cart findCartById(Long id) throws Exception;

    // public Cart findCartByUserId(Long id)throws Exception;

    // public Cart clearCart(Long userId)throws Exception;



    

}
