package com.retail.ordering.system.prodcutcatalogservice.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI ProductCatalogServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product Catalog Service API")
                        .description("This is a API for the demonstration of Product Catalog using Boot .")
                        .version("v0.1"));

    }

}
