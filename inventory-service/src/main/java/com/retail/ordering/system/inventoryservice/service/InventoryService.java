package com.retail.ordering.system.inventoryservice.service;


import com.retail.ordering.system.inventoryservice.domain.InventoryItem;
import com.retail.ordering.system.inventoryservice.repositories.InventoryItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InventoryService {

    private final InventoryItemRepository inventoryItemRepository;

    public InventoryService(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    public List<InventoryItem> getInventoryList() {
        return inventoryItemRepository.findAll();
    }

    public Optional<InventoryItem> findByProductCode(String productCode) {
            return inventoryItemRepository.findByProductCode(productCode);
    }
}
