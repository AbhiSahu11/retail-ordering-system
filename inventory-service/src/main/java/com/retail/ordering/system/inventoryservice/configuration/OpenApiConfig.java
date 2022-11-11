package com.retail.ordering.system.inventoryservice.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI InventoryServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Inventory Service API")
                        .description("This is a API for the demonstration of Product Inventory using Boot .")
                        .version("v0.1"));

    }

}
