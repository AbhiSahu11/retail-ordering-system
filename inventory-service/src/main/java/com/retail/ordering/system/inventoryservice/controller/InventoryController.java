package com.retail.ordering.system.inventoryservice.controller;


import com.retail.ordering.system.inventoryservice.domain.InventoryItem;
import com.retail.ordering.system.inventoryservice.exception.InventoryNotFoundException;
import com.retail.ordering.system.inventoryservice.service.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Inventory Service API", description = "  Inventory Service API is used to provide product inventory for Retail ordering System")
@RestController
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Operation(summary = "Get All Product Inventory from DB")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK - Product Inventory List is retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request is not valid"),
    })
    @GetMapping("/api/inventory")
    public List<InventoryItem> getInventory() {
        return inventoryService.getInventoryList();
    }


    @Operation(summary = "Get All Product Inventory from DB based on product code")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK - Product Inventory is retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request - The request is not valid"),
    })
    @GetMapping("/api/inventory/{productCode}")
    public InventoryItem findInventoryByProductCode(@PathVariable("productCode") String productCode) {
        log.info("Finding inventory for product code: {}", productCode);
        Optional<InventoryItem> inventoryItem = inventoryService.findByProductCode(productCode);
        return inventoryItem.orElseThrow(()-> new InventoryNotFoundException("inventory not found for productcode:{}",productCode));
    }


}
