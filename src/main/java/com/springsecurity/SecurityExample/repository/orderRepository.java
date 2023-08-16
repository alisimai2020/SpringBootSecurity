package com.springsecurity.SecurityExample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.SecurityExample.model.Order;

public interface orderRepository extends JpaRepository<Order,String> {
    // List<Order> findAllByOrderByCreatedAtDesc();

    //  List<Order> findByIdContainingOrDescriptionContainingIgnoreCaseOrderByCreatedAt(String text, String text2) ;
}
