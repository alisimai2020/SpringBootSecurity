package com.springsecurity.SecurityExample.service;

import java.util.List;

import com.springsecurity.SecurityExample.model.Order;
import com.springsecurity.SecurityExample.model.User;

public interface OrderService {

    // List<Order> getOrders();

    // List<Order> getOrdersContainingText(String text);

    Order validateAndGetOrder(String id);

    Order saveOrder(Order order);

    void deleteOrder(String id);

    Order updateOrder(String id, Order order);
    String addUser(User user);
}
