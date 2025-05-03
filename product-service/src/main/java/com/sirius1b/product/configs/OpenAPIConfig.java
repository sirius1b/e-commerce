package com.sirius1b.product.configs;

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
                        .title("E-Commerce Product")
                        .version("v1.0")
                        .description("Product APIs"));
    }
}



