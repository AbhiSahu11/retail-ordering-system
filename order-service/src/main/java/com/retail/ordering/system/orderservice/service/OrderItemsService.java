package com.retail.ordering.system.orderservice.service;

import com.retail.ordering.system.orderservice.entities.OrderItem;
import com.retail.ordering.system.orderservice.repository.OrderItemsRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class OrderItemsService {

    private final OrderItemsRepository orderItemsRepository;

    public OrderItemsService(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    public void addOrderedProducts(OrderItem orderItem) {
        this.orderItemsRepository.save(orderItem);
    }


}
