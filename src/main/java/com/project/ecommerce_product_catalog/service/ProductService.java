package com.project.ecommerce_product_catalog.service;

import com.project.ecommerce_product_catalog.model.Product;
import com.project.ecommerce_product_catalog.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;
    public List<Product> showProducts() {
        return repository.findAll();
    }

    public void addProduct(Product product) {
        repository.save(product);
    }

    public void updateProduct(int id,Product product) {
        repository.save(product);
    }

    public Product getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteProduct(int id) {
        repository.deleteById(id);
    }
}
