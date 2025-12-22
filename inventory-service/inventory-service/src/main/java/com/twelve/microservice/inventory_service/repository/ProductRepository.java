package com.twelve.microservice.inventory_service.repository;

import com.twelve.microservice.inventory_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
