// package com.service;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collector;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.milind.model.Address;
// import com.milind.model.Cart;
// import com.milind.model.CartItem;
// import com.milind.model.Order;
// import com.milind.model.OrderItem;
// import com.milind.model.Restaurant;
// import com.milind.model.User;
// import com.repository.AddressRepository;
// import com.repository.OrderItemRepository;
// import com.repository.OrderRepository;
// import com.repository.RestaurantRepository;
// import com.repository.UserRepository;
// import com.request.OrderRequest;

// @Service
// public class OrderServiceImpl implements OrderService{
    
//     @Autowired
//     private OrderRepository orderRepository;

//     @Autowired
//     private OrderItemRepository orderItemRepository;

//     @Autowired
//     private AddressRepository addressRepository;

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private RestaurantService restaurantService;

//     @Autowired
//     private CartService CartService;

//     @Override
//     public Order createOrder(OrderRequest order,User user){
//         Address shippAddress=order.getDeliveryAddress();

//         Address savedAddress = addressRepository.save(shippAddress);

//         if(!user.getAddresses().contains(savedAddress)){
//             user.getAddresses().add(savedAddress);
//             userRepository.save(user);
//         }
//         Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());

//         Order createOrder = new Order();
//         createOrder.setCustomer(user);
//         createOrder.setCreatedAt(new Date());
//         createOrder.setOrderStatus("PENDING");
//         createOrder.setDeliveryAddress(savedAddress);
//         createOrder.setRestaurant(restaurant);

//         Cart cart=cartService.findCartByUserId(user.getId());

//         List<OrderItem> orderItems = new ArrayList<>();

//         for(CartItem cartItem : cart.getItem()){
//             OrderItem orderItem = new OrderItem();
//             orderItem.setFood(cartItem.getFood());
//             orderItem.setIntgredients(cartItem.getIngredients());
//             orderItem.setQuantity(cartItem.getIngredients());
//             orderItem.setTotalPrice(cartItem.getTotalPrice());

//             OrderItem savedOrderItem=orderitemRepository.save(orderitem);
//             orderItems.add(savedOrderItem);

//         }
//         Long totalPrice=cartService.calculateCartTotals(cart);

//         createdOrder.setItems(orderItems);
//         createdOrder.setTotalPrice(totalPrice);

//         Order savedOrder=orderRepository.save(createdOrder);
//         restaurant.getOrders().add(savedOrder);
 
//         return null;

//     }

//     @Override
//     public Order updateOrder(Long orderId, String orderStatus) throws Exception{
//         Order order = findOrderById(orderId);
//         if(orderStatus.equals("OUT_FOR_DELIVERY")
//         || orderStatus.equals("DELIVERED")
//         || orderStatus.equals("COMPLETED")
//         || orderStatus.equals("PENDING")
//         ){
//             order.setOrderStatus(orderStatus);
//             return orderRepository.save(order);
//         }
//         throw new Exception("Please select a valid order status");
//     }

//     @Override
//     public void cancelOrder(Long orderId) throws Exception{
//         Order order =findOrderById(orderId);
//         orderRepository.deleteById(orderId);
//     }

//     @Override
//     public List<Order> getUsersOrder(Long userId) throws Exception{
//         return orderRepository.findByCustomerId(userId);
//     }

//     @Override
//     public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception{
//         List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
//         if(orderStatus!= null){
//             orders = orders.stream().filter(order->
//             order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
//         }
//         return orders;
//     }

//     @Override
//     public Order findOrderById (Long orderId) throws Exception{
//         Optional<Order> optionalOrder=orderRepository.findById(orderId);
//         if(optionalOrder.isEmpty()){
//             throw new Exception("order not found");
//         }
//         return optionalOrder.get();
//     }


// }





package com.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milind.model.Address;
import com.milind.model.Cart;
import com.milind.model.CartItem;
import com.milind.model.Order;
import com.milind.model.OrderItem;
import com.milind.model.Restaurant;
import com.milind.model.User;
import com.repository.AddressRepository;
import com.repository.OrderItemRepository;
import com.repository.OrderRepository;
import com.repository.UserRepository;
import com.request.OrderRequest;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderRequest order, User user) {
        Address shippingAddress = order.getDeliveryAddress();
        Address savedAddress = addressRepository.save(shippingAddress);

        if (!user.getAddresses().contains(savedAddress)) {
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }

        Restaurant restaurant = restaurantService.findRestaurantById(order.getRestaurantId());

        Order createOrder = new Order();
        createOrder.setCustomer(user);
        createOrder.setCreatedAt(new Date());
        createOrder.setOrderStatus("PENDING");
        createOrder.setDeliveryAddress(savedAddress);
        createOrder.setRestaurant(restaurant);

        Cart cart = cartService.findCartByUserId(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItem()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setIntgredients(cartItem.getIngredients());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getTotalPrice());

            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }

        Long totalPrice = cartService.calculateCartTotals(cart);
        createOrder.setItems(orderItems);
        createOrder.setTotalPrice(totalPrice);

        Order savedOrder = orderRepository.save(createOrder);
        restaurant.getOrders().add(savedOrder);

        return savedOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        if (orderStatus.equals("OUT_FOR_DELIVERY") || 
            orderStatus.equals("DELIVERED") || 
            orderStatus.equals("COMPLETED") || 
            orderStatus.equals("PENDING")) {
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("Please select a valid order status");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUsersOrder(Long userId) {
        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) {
        List<Order> orders = orderRepository.findByRestaurantId(restaurantId);
        if (orderStatus != null) {
            orders = orders.stream()
                    .filter(order -> order.getOrderStatus().equals(orderStatus))
                    .collect(Collectors.toList());
        }
        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new Exception("Order not found");
        }
        return optionalOrder.get();
    }
}
