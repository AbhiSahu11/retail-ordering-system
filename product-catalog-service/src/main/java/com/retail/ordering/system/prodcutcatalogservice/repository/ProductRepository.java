package com.retail.ordering.system.prodcutcatalogservice.repository;

import com.retail.ordering.system.prodcutcatalogservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByCode(String code);
}
