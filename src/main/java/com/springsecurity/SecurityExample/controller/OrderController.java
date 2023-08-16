package com.springsecurity.SecurityExample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.springsecurity.SecurityExample.model.Order;
import com.springsecurity.SecurityExample.model.User;
import com.springsecurity.SecurityExample.repository.orderRepository;
import com.springsecurity.SecurityExample.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private  OrderService orderService;

    @Autowired
    private orderRepository oRepository;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Order> getAllOrder(){
        return oRepository.findAll();
    }

  
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("new")
    public String AddUser(@RequestBody User user){
        return orderService.addUser(user);
    }
    // @GetMapping("/search")
    // public ResponseEntity<List<Order>> searchOrders(@RequestParam(name = "text") String text) {
    //     List<Order> orders = orderService.getOrdersContainingText(text);
    //     return ResponseEntity.ok(orders);
    // }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Order> getOrder(@PathVariable String id) {
        Order order = orderService.validateAndGetOrder(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.saveOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(id, order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
