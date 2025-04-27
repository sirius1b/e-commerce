package com.sirius1b.auth.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce Auth")
                        .version("v1.0")
                        .description("Authentication APIs"));
    }
}



