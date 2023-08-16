package com.springsecurity.SecurityExample.service;

import java.util.List;
import java.util.Optional;

import com.springsecurity.SecurityExample.model.User;

public interface UserService {

    List<User> getUsers();
    Optional<User> getUserByUsername(String username);

    User validateAndGetUser(Long id);

    User saveUser(User user);

    User updateUser(Long id, User updatedUser);

    void deleteUser(Long id);
}
    

