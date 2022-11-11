package com.retail.ordering.system.orderservice.repository;


import com.retail.ordering.system.orderservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserName(String userName);

    boolean existsByUserName(String userName);

}
