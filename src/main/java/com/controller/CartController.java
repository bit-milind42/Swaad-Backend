// package com.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestHeader;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.milind.model.Cart;
// import com.milind.model.CartItem;
// import com.milind.model.User;
// import com.request.AddCartItemRequest;
// import com.request.UpdateCartItemRequest;
// import com.service.CartService;
// import com.service.UserService;

// @RestController
// @RequestMapping("/api")
// public class CartController {

//     @Autowired
//     private CartService cartService;

//     @Autowired
//     private UserService userService;

//     @PutMapping("/cart/add")
//     public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest req,
//         @RequestHeader("Authorization") String jwt) throws Exception {
//         CartItem cartItem=cartService.addItemToCart(req, jwt);
//         return new ResponseEntity<>(cartItem, HttpStatus.OK);
//     }

//     @PutMapping("/cart-item/update")
//     public ResponseEntity<CartItem> updateCartItemQuantity(
//         @RequestBody UpdateCartItemRequest req,
//         @RequestHeader("Authorization") String jwt) throws Exception {
//         CartItem cartItem=cartService.updateCartItemQuatity(req.getCartItemId(), req.getQuanity());
//         return new ResponseEntity<>(cartItem, HttpStatus.OK);
//     }

//     @DeleteMapping("/cart-item/{id}/remove")
//     public ResponseEntity<CartItem> removeCartItem(
//         @RequestBody Long id,
//         @RequestHeader("Authorization") String jwt) throws Exception {
//         Cart cart=cartService.removeItemFromCart(id, jwt);
//         return new ResponseEntity<>(cart, HttpStatus.OK);
//     }


//     @PutMapping("/cart/clear")
//     public ResponseEntity<Cart> clearCart(
        
//         @RequestHeader("Authorization") String jwt) throws Exception {
//         Cart cart=cartService.clearCart(user.getId());
//         return new ResponseEntity<>(cart, HttpStatus.OK);
//     }

//     @GetMapping("/cart")
//     public ResponseEntity<Cart> findUserCart(
        
//         @RequestHeader("Authorization") String jwt) throws Exception {
//             User user=userService.findUserByJwtToken(jwt);
//             Cart cart=cartService.findCartByUserId(jwt);
//             return new ResponseEntity<>(cart, HttpStatus.OK);
//     }
// }







package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.milind.model.Cart;
import com.milind.model.CartItem;
import com.milind.model.User;
import com.request.AddCartItemRequest;
import com.request.UpdateCartItemRequest;
import com.service.CartService;
import com.service.UserService;

@RestController
@RequestMapping("/api")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PutMapping("/cart/add")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest req,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cartItem = cartService.addItemToCart(req, jwt);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/cart-item/update")
    public ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody UpdateCartItemRequest req,
                                                           @RequestHeader("Authorization") String jwt) throws Exception {
        CartItem cartItem = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<Cart> removeCartItem(@PathVariable Long id,
                                               @RequestHeader("Authorization") String jwt) throws Exception {
        Cart cart = cartService.removeItemFromCart(id, jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/cart/clear")
    public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.clearCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/cart")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
