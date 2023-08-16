package com.springsecurity.SecurityExample.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
public class LoginRequest {
   
    private String email;
    private String password;
 
}
