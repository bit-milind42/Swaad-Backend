package com.service;

import java.util.List;

import com.milind.model.Order;
import com.milind.model.User;
import com.request.OrderRequest;

public interface OrderService {

    public Order createOrder(OrderRequest order,User user);

    public Order updateOrder(Long orderId, String orderStatus) throws Exception;

    public void cancelOrder(Long orderId) throws Exception;

    public List<Order> getUsersOrder(Long userId) throws Exception;

    public List<Order> getRestaurantsOrder(Long restaurantId, String orderStatus) throws Exception;

    public Order findOrderById (Long orderId) throws Exception;
} 