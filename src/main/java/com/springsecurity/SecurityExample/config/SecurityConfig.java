package com.springsecurity.SecurityExample.config;

import org.hibernate.annotations.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

// import com.springsecurity.SecurityExample.model.User;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
//  @Bean
 //authentication
 @Bean
public UserDetailsService userDetailsService(){
        //   public UserDetailsService userDetailsService(PasswordEncoder encoder){
    //     UserDetails admin =User.withUsername("Dapry")
    //     .password(encoder.encode("Pw1"))
    //     .roles("ADMIN","USER","HR")
    //     .build();


    //      UserDetails user =User.withUsername("Prince")
    //     .password(encoder.encode("Pw2"))
    //     .roles("USER")
    //     .build();
    //     return new InMemoryUserDetailsManager(admin,user);
        return new UserInfoUserDetailsService();
}

//authorization
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
   return http.csrf().disable()
    .authorizeHttpRequests()
    .requestMatchers("/api/orders/welcome","/api/orders/new").permitAll()
    .and()
    .authorizeHttpRequests().requestMatchers("/api/orders/**").authenticated()
    .and().formLogin()
    .and().build();
}
@Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    
// An AuthenticationProvider is a fundamental concept in Spring Security, a powerful framework used for
//  handling authentication and authorization in Java applications. It is responsible for authenticating a user's credentials (such as username and password) 
// and providing an authenticated Authentication object if the credentials are valid
    
// Key features of DaoAuthenticationProvider include:

// UserDetailsService Integration: It works in tandem with a UserDetailsService implementation,
//  which is responsible for loading user details from a user store based on a provided username. 
//  The UserDetailsService provides the necessary user information, including the stored password hash.

// Password Encoding: DaoAuthenticationProvider supports password hashing and matching.
//  When authenticating a user, it retrieves the stored password hash from the user details
//   obtained through the UserDetailsService, and then uses a PasswordEncoder to compare
//    the provided password with the stored hash.

// Authentication Event Handling: It reports authentication events to Spring Security's event publishing system, 
// allowing you to capture and react to successful or failed authentication attempts.


@Bean
public AuthenticationProvider  authenticationProvider(){
        // DaoAuthenticationProvider is a concrete implementation of the Spring Security AuthenticationProvider interface. It is designed to authenticate users by 
        // retrieving their credentials (username and password) from a data source using a UserDetailsService and then comparing the provided credentials with the stored values.
        DaoAuthenticationProvider authenticationProvider  = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;      
   
    }
}
