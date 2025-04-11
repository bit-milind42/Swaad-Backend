package com.request;

import java.util.List;

import com.milind.model.Cart;
import com.milind.model.CartItem;

import lombok.Data;

@Data
public class AddCartItemRequest {

    private Long foodId;
    private int quantity;
    private List<String> ingredients;
    
   
}
