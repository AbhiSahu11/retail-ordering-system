package com.retail.ordering.system.orderservice.repository;

import com.retail.ordering.system.orderservice.entities.Order;
import com.retail.ordering.system.orderservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Integer> {
    List<Order> findAllByUserOrderByCreatedDateDesc(User user);

}
