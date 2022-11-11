package com.retail.ordering.system.inventoryservice.repositories;


import com.retail.ordering.system.inventoryservice.domain.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    Optional<InventoryItem> findByProductCode(String productCode);
}
