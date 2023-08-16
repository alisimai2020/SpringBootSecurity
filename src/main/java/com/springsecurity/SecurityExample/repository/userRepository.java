package com.springsecurity.SecurityExample.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.SecurityExample.model.User;

public interface userRepository  extends JpaRepository<User,Long>{
    

    Optional<User> findByusername(String username);

    boolean existsByUsername(String username);

    public User findByEmail(String email);
    
}
