package com.sirius1b.auth.configs;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "app.admin")
public record SecureAdminConfig(String email, String username, String password) {

}
