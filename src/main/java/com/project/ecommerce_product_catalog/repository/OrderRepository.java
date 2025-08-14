package com.project.ecommerce_product_catalog.repository;

import com.project.ecommerce_product_catalog.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
