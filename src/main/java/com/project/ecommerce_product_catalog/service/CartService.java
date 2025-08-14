package com.project.ecommerce_product_catalog.service;

import com.project.ecommerce_product_catalog.model.Product;
import com.project.ecommerce_product_catalog.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private CartRepository repository;
    public void CartService(CartRepository repository){
        this.repository=repository;
    }
    private final Logger logger= LoggerFactory.getLogger(CartService.class);
    public List<Product> getProducts(Long userId) {
        return null;
    }

    public void addCart(Product product) {

    }

    public void updateCart(int id, Product product) {
    }

    public void removeitem(int id) {
    }

    public void clearcart(int id) {
    }
}
