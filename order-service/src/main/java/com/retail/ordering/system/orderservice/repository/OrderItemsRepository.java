package com.retail.ordering.system.orderservice.repository;

import com.retail.ordering.system.orderservice.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItem,Integer> {
}
