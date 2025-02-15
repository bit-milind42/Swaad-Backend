package com.service;

import java.lang.classfile.ClassFile.Option;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.milind.model.Cart;
import com.milind.model.CartItem;
import com.milind.model.Food;
import com.milind.model.User;
import com.repository.CartItemRepository;
import com.repository.CartRepository;
import com.repository.FoodRepository;
import com.request.AddCartItemRequest;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user= userService.findUserByJwtToken(jwt);
        Food food = foodService.findFoodById(req.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());

        for(CartItem cartItem : cart.getItem()){
            if(cartItem.getFood().equals(food)){
                int newQuantity= cartItem.getQuantity()+req.getQuanity();
                return updateCartItemQuality(cartItem.getId(), newQuantity);
            }
        }

        CartItem newCartItem=new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(req.getQuanity());
        newCartItem.setIngredients(req.getIngredients());
        newCartItem.setTotalPrice(req.getQuanity()*food.getPrice());

        CartItem savedCartItem = cartItemRepository.save(newCartItem);
        cart.getItems().add(savedCartItem);
        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuality(Long cartItemId,int quanity) throws Exception{
        Optional<CartItem> cartItemOptional=cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception("Cart item not fount");
        }
        CartItem item = cartItemOptional.get();
        item.setQuantity(quanity);
// 5*100=500
        item.setTotalPrice(item.getFood().getPrice()*quantity);

        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception{
        User user= userService.findUserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItem> cartItemOptional=cartItemRepository.findById(cartItemId);
        if(cartItemOptional.isEmpty()){
            throw new Exception("Cart item not fount");
        }

        CartItem item=cartItemOptional.get();
        cart.getItems().remove(item);
        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception{
        Long total= 0L;

        for(CartItem cartItem : cart.getItem()){
            total+=cartItem.getFood().getPrice()*cartItem.getQuantity();
        }
        return total;

    }

    @Override
    public Cart findCartById(Long id) throws Exception{
        Optional<Cart> optionalCart=cartRepository.findById(id);
        if(optionalCart.isEmpty()){
            throw new Exception("cart not found with id" +id);
        }
        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(Long userId)throws Exception{
        // User user=userService.findUserByJwtToken(jwt);

        Cart cart=cartRepository.findByCustomerId(userId);
        cart.setTotal(calculateCartTotals(cart));
        return cart;
    }

    @Override
    public Cart clearCart(Long userId)throws Exception{
        // User user=userService.findUserByJwtToken(userId);
        Cart cart=findCartByUserId(userId);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }


}







// package com.service;

// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import com.milind.model.Cart;
// import com.milind.model.CartItem;
// import com.milind.model.Food;
// import com.milind.model.User;
// import com.repository.CartItemRepository;
// import com.repository.CartRepository;
// import com.request.AddCartItemRequest;

// @Service
// public class CartServiceImpl implements CartService {

//     @Autowired
//     private CartRepository cartRepository;

//     @Autowired
//     private UserService userService;

//     @Autowired
//     private CartItemRepository cartItemRepository;

//     @Autowired
//     private FoodService foodService;

//     @Override
//     public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
//         User user = userService.findUserByJwtToken(jwt);
//         Food food = foodService.findFoodById(req.getFoodId());
//         Cart cart = cartRepository.findByCustomerId(user.getId());

//         if (cart == null) {
//             cart = new Cart();
//             cart.setCustomer(user);
//             cart = cartRepository.save(cart);
//         }

//         for (CartItem cartItem : cart.getItems()) {
//             if (cartItem.getFood().equals(food)) {
//                 int newQuantity = cartItem.getQuantity() + req.getQuantity();
//                 return updateCartItemQuantity(cartItem.getId(), newQuantity);
//             }
//         }

//         CartItem newCartItem = new CartItem();
//         newCartItem.setFood(food);
//         newCartItem.setCart(cart);
//         newCartItem.setQuantity(req.getQuantity());
//         newCartItem.setIngredients(req.getIngredients());
//         newCartItem.setTotalPrice(req.getQuantity() * food.getPrice());

//         CartItem savedCartItem = cartItemRepository.save(newCartItem);
//         cart.getItems().add(savedCartItem);
//         return savedCartItem;
//     }

//     @Override
//     public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
//         Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
//         if (cartItemOptional.isEmpty()) {
//             throw new Exception("Cart item not found");
//         }
//         CartItem item = cartItemOptional.get();
//         item.setQuantity(quantity);
//         item.setTotalPrice(item.getFood().getPrice() * quantity);

//         return cartItemRepository.save(item);
//     }

//     @Override
//     public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
//         User user = userService.findUserByJwtToken(jwt);
//         Cart cart = cartRepository.findByCustomerId(user.getId());

//         if (cart == null) {
//             throw new Exception("Cart not found for user");
//         }

//         Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
//         if (cartItemOptional.isEmpty()) {
//             throw new Exception("Cart item not found");
//         }

//         CartItem item = cartItemOptional.get();
//         cart.getItems().remove(item);
//         cartItemRepository.delete(item);

//         return cartRepository.save(cart);
//     }

//     @Override
//     public Long calculateCartTotals(Cart cart) throws Exception {
//         Long total = 0L;

//         if (cart != null && cart.getItems() != null) {
//             for (CartItem cartItem : cart.getItems()) {
//                 total += cartItem.getFood().getPrice() * cartItem.getQuantity();
//             }
//         }
//         return total;
//     }

//     @Override
//     public Cart findCartById(Long id) throws Exception {
//         Optional<Cart> optionalCart = cartRepository.findById(id);
//         if (optionalCart.isEmpty()) {
//             throw new Exception("Cart not found with id " + id);
//         }
//         return optionalCart.get();
//     }

//     @Override
//     public Cart findCartByUserId(Long userId) throws Exception {
//         Cart cart = cartRepository.findByCustomerId(userId);

//         if (cart == null) {
//             throw new Exception("Cart not found for user ID: " + userId);
//         }

//         cart.setTotal(calculateCartTotals(cart));  // Ensure `Cart` model has `setTotal()`
//         return cart;
//     }

//     @Override
//     public Cart clearCart(Long userId) throws Exception {
//         Cart cart = findCartByUserId(userId);
//         cart.getItems().clear();
//         return cartRepository.save(cart);
//     }
// }
