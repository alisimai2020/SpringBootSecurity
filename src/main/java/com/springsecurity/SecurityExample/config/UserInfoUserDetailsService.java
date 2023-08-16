package com.springsecurity.SecurityExample.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.springsecurity.SecurityExample.model.User;
import com.springsecurity.SecurityExample.repository.userRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private userRepository uRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = uRepository.findByusername(username);

        return user.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
