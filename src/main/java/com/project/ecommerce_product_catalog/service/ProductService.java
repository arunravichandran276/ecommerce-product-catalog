package com.project.ecommerce_product_catalog.service;

import com.project.ecommerce_product_catalog.model.Product;
import com.project.ecommerce_product_catalog.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
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
        try {
            repository.save(product);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to add a product " + e.getMessage());
        }
    }

    public void updateProduct(int id,Product product) {
        try{
            Product existing=repository.findById(id).orElseThrow(()->new RuntimeException("Failed to" +
                    "get the product with id "+id));
            existing.setName(product.getName());
            repository.save(existing);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to update a product "+e.getMessage());
        }

    }

    public Product getProductById(int id) {
        try{
            return repository.findById(id).orElse(null);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to get the product with ID "+id+" "+e.getMessage());
        }
    }

    public void deleteProduct(int id) {
        try{
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete the product " +e.getMessage());
        }
    }
}
