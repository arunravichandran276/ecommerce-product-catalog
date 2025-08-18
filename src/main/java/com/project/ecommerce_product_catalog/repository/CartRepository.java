package com.project.ecommerce_product_catalog.repository;

import com.project.ecommerce_product_catalog.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUser_Id(Long userId);
}
