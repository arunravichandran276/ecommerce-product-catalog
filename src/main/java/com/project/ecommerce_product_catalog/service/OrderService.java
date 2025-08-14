package com.project.ecommerce_product_catalog.service;

import com.project.ecommerce_product_catalog.model.Product;
import com.project.ecommerce_product_catalog.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository repository;
    public void OrderService(OrderRepository repository){
        this.repository=repository;
    }

    public List<Product> getOrders(int orderid) {
        return null;
    }
}
