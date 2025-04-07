package com.sirius1b.auth.configs;


import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "security.jwt")
public record SecurityJwtConfig(String secretKey, long expirationTime) {

}
