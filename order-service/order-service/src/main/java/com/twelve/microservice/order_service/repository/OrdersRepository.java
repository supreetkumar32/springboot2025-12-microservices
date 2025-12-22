package com.twelve.microservice.order_service.repository;

import com.twelve.microservice.order_service.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
