package com.retail.ordering.system.orderservice.service;

import com.retail.ordering.system.orderservice.dto.cart.CartProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceClient {

    private final RestTemplate restTemplate;
    private final ProductServiceFeignClient productServiceFeignClient;

    private static final String CATALOG_API_PATH = "http://catalog-service/api/";

    public ProductServiceClient(RestTemplate restTemplate, ProductServiceFeignClient productServiceFeignClient) {
        this.restTemplate = restTemplate;
        this.productServiceFeignClient = productServiceFeignClient;
    }

    public CartProductResponse productByCode(String productCode){
        ResponseEntity<CartProductResponse> itemResponseEntity = restTemplate.getForEntity(CATALOG_API_PATH + "/products/{code}",
                CartProductResponse.class,productCode);
        return itemResponseEntity.getBody();
    }


}
