package com.service;
import com.milind.model.Cart;
import com.milind.model.CartItem;
import com.request.AddCartItemRequest;

public interface CartService {

    public Cart findByCustomerId(Long userId);

    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception;

    public CartItem updateCartItemQuality(Long cartItemId,int quanity) throws Exception;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;

    public Long calculateCartTotals(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws Exception;

    public Cart findCartByUserId( Long userId)throws Exception;

    public Cart clearCart(Long userId)throws Exception;

} 
