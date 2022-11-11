package com.retail.ordering.system.inventoryservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventory")
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_code", nullable = false, unique = true)
    private String productCode;

    @Column(name = "quantity")
    private Integer availableQuantity = 0;

}
