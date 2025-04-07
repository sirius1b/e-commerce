package com.sirius1b.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class AuthService {

	public static void main(String[] args) {
		SpringApplication.run(AuthService.class, args);
	}

}
	