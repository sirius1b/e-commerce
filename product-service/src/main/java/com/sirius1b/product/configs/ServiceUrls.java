package com.sirius1b.product.configs;


import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "service.address")
public record ServiceUrls(String auth) {
}
