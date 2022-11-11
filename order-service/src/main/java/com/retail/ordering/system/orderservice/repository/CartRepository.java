package com.retail.ordering.system.orderservice.repository;

import com.retail.ordering.system.orderservice.entities.Cart;
import com.retail.ordering.system.orderservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
    List<Cart> deleteByUser(User user);
}

