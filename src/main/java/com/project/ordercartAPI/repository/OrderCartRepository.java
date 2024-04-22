package com.project.ordercartAPI.repository;

import com.project.ordercartAPI.model.OrderCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCartRepository extends JpaRepository<OrderCart, Long> {

}
