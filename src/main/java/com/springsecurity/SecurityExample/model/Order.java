package com.springsecurity.SecurityExample.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor

@Table(name = "orders")
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

  // @Temporal(TemporalType.TIMESTAMP) // Add this annotation
  //   @Column(name = "created_at")     // Add this annotation
  //   private Date createdAt;           // Add this property

// @ManyToOne
//  @JoinColumn(name = "user_id")
//     private User user;



}
