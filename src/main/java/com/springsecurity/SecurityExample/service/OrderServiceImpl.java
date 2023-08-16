package com.springsecurity.SecurityExample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springsecurity.SecurityExample.exception.OrderNotFoundException;
import com.springsecurity.SecurityExample.model.Order;
import com.springsecurity.SecurityExample.model.User;
import com.springsecurity.SecurityExample.repository.orderRepository;
import com.springsecurity.SecurityExample.repository.userRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService  {

    @Autowired
    private final orderRepository oRepository;

    @Autowired
    private final userRepository uRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    // @Override
    // public List<Order> getOrders() {
    //     return oRepository.findAllByOrderByCreatedAtDesc();
    // }

    // @Override
    // public List<Order> getOrdersContainingText(String text) {
    //     return oRepository.findByIdContainingOrDescriptionContainingIgnoreCaseOrderByCreatedAt(text, text);
    // }
    @Override
    public Order validateAndGetOrder(String id) {
        Optional<Order> getOrder = oRepository.findById(id);
        return getOrder.orElseThrow(() -> new OrderNotFoundException(String.format("Order with id %s not found", id)));
    }
    

    @Override
    public Order saveOrder(Order order) {
        return oRepository.save(order);
    }

    @Override
    public void deleteOrder(String id) {
        Order order = validateAndGetOrder(id);
        oRepository.delete(order);
    }

    @Override
    public Order updateOrder(String id, Order updatedOrder) {
        Order existingOrder = validateAndGetOrder(id);
        existingOrder.setDescription(updatedOrder.getDescription());
        // existingOrder.setUser(updatedOrder.getUser());
        return oRepository.save(existingOrder);
    }

    @Override
    public String addUser(User user) {
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("User password cannot be null");
        }
    
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        uRepository.save(user);
    
        return "user added to system";
    }
}

