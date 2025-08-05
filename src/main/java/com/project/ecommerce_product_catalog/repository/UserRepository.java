package com.project.ecommerce_product_catalog.repository;

import com.project.ecommerce_product_catalog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
