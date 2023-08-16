package com.springsecurity.SecurityExample.service;

import com.springsecurity.SecurityExample.exception.UserNotFoundException;
import com.springsecurity.SecurityExample.model.User;

import com.springsecurity.SecurityExample.repository.userRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
     @Autowired
    private  userRepository uRepository;

   
  
    @Override
    public List<User> getUsers() {
        return uRepository.findAll();
    }

    @Override
    public User validateAndGetUser(Long id) {
        return uRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id %s not found", id)));
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return uRepository.findByusername(username);
    }

    @Override
    public User saveUser(User user) {
        return uRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        User existingUser = validateAndGetUser(id);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setRole(updatedUser.getRole());
        return uRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = validateAndGetUser(id);
        uRepository.delete(user);
    }
}
