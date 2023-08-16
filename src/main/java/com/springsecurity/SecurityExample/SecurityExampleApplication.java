package com.springsecurity.SecurityExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.springsecurity.SecurityExample")
public class SecurityExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityExampleApplication.class, args);
	}

}
