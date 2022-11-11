package com.retail.ordering.system.orderservice.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI OrderServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Order Service API")
                        .description("This is a Order Service API for ordering using retail ordering system .")
                        .version("v0.1"));

    }

}
