package com.retail.ordering.system.prodcutcatalogservice.service;

import com.retail.ordering.system.prodcutcatalogservice.entities.Product;
import com.retail.ordering.system.prodcutcatalogservice.entities.ProductInventoryResponse;
import com.retail.ordering.system.prodcutcatalogservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final InventoryServiceClient inventoryServiceClient;

    public ProductService(ProductRepository productRepository, InventoryServiceClient inventoryServiceClient) {
        this.productRepository = productRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    public List<Product> findAllProducts() {
        List<Product> products = productRepository.findAll();
        final Map<String, Integer> inventoryLevels = getInventoryLevelsWithFeignClient();
        return products.stream()
                .filter(p -> inventoryLevels.get(p.getCode()) != null && inventoryLevels.get(p.getCode()) > 0)
                .collect(Collectors.toList());
    }

    private Map<String, Integer> getInventoryLevelsWithFeignClient() {
        Map<String, Integer> inventoryLevels = new HashMap<>();
        List<ProductInventoryResponse> inventory = inventoryServiceClient.getProductInventoryLevels();
        for (ProductInventoryResponse item: inventory){
            inventoryLevels.put(item.getProductCode(), item.getAvailableQuantity());
        }
        return inventoryLevels;
    }

    public Optional<Product> findProductByCode(String code) {
        Optional<Integer> quantity =this.inventoryServiceClient.getProductInventoryByCode(code).map(ProductInventoryResponse::getAvailableQuantity);
        Optional<Product> productOptional = productRepository.findByCode(code);
        if(quantity.isPresent() && quantity.get() <10) {
            productOptional.get().isLowAvailability(true);
        }
        return productOptional;
    }
}
