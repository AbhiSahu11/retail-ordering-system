package com.retail.ordering.system.orderservice.service;

import com.retail.ordering.system.orderservice.dto.cart.CartProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "catalog-service")
public interface ProductServiceFeignClient {

    @GetMapping("/api/products")
    List<CartProductResponse> allProducts();

    @GetMapping("/api/products/{code}")
    CartProductResponse productByCode(@PathVariable String code);

}
