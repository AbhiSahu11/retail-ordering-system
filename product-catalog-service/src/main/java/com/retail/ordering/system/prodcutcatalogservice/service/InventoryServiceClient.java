package com.retail.ordering.system.prodcutcatalogservice.service;


import com.retail.ordering.system.prodcutcatalogservice.entities.ProductInventoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class InventoryServiceClient {

    private final RestTemplate restTemplate;
    private final InventoryServiceFeignClient inventoryServiceFeignClient;
    private static final String INVENTORY_API_PATH = "http://inventory-service/api/";

    public InventoryServiceClient(RestTemplate restTemplate, InventoryServiceFeignClient inventoryServiceFeignClient) {
        this.restTemplate = restTemplate;
        this.inventoryServiceFeignClient = inventoryServiceFeignClient;
    }
    public List<ProductInventoryResponse> getProductInventoryLevels() {
        return this.inventoryServiceFeignClient.getInventoryLevels();
    }

    public Optional<ProductInventoryResponse> getProductInventoryByCode(String productCode){

        ResponseEntity<ProductInventoryResponse> itemResponseEntity = restTemplate.getForEntity(INVENTORY_API_PATH + "inventory/{code}",
                                                        ProductInventoryResponse.class,productCode);
        if (itemResponseEntity.getStatusCode() == HttpStatus.OK) {
            int quantity = Objects.requireNonNull(itemResponseEntity.getBody()).getAvailableQuantity();
            log.info("Available quantity: {} ",quantity);
        }
        return Optional.ofNullable(itemResponseEntity.getBody());
    }



}
