package com.retail.ordering.system.prodcutcatalogservice.entities;


import lombok.Data;

@Data
public class ProductInventoryResponse {

    private String productCode;
    private int availableQuantity;

}