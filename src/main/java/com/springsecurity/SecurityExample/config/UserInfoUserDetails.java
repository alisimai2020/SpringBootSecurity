package com.springsecurity.SecurityExample.config;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springsecurity.SecurityExample.model.User;

public class UserInfoUserDetails  implements UserDetails{

    private String username;
    private String password;

    private List<GrantedAuthority> authorities;

    public UserInfoUserDetails(User user) {

//authorities = Arrays.stream(user.getRole().split(",")) creates a stream of strings from the role of the user. The role is a comma-separated list of roles, so this line of code splits the role into a stream of strings.
// .map(SimpleGrantedAuthority::new) maps each string in the stream to a SimpleGrantedAuthority object. A SimpleGrantedAuthority object represents a role in Spring Security.
// .collect((Collectors.toList())); collects the SimpleGrantedAuthority objects into a lis


        username = user.getUsername();
        password = user.getPassword();
        
        //for a single role
        // this.authorities = Arrays.stream(user.getRoles())
        // .map(SimpleGrantedAuthority::new)
        // .collect(Collectors.toSet());


        authorities = Arrays.stream(user.getRole().split(","))
    .map(SimpleGrantedAuthority::new )
    .collect((Collectors.toList()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
       return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
       return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
       return true;
    }
    
}
