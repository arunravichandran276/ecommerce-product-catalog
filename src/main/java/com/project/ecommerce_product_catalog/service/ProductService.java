package com.project.ecommerce_product_catalog.service;

import com.project.ecommerce_product_catalog.dto.ProductDTO;
import com.project.ecommerce_product_catalog.model.Product;
import com.project.ecommerce_product_catalog.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
            product.setCreatedAt(LocalDateTime.now());
            product.setUpdatedAt(LocalDateTime.now());
            repository.save(product);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to add a product " + e.getMessage());
        }
    }

    public void updateProduct(int id, ProductDTO dto) {
        try{
            Product existing=repository.findById(id).orElseThrow(()->new RuntimeException("Failed to" +
                    "get the product with id "+id));
            if(dto.getName()!=null){
                existing.setName( dto.getName());
            }
            if(dto.getTags()!=null){
                existing.setTags((ArrayList<String>) dto.getTags());
            }if(dto.getDescription()!=null){
                existing.setDescription( dto.getDescription());
            }if(dto.getCategory()!=null){
                existing.setCategory( dto.getCategory());
            }if(dto.getPrice()!=null){
                existing.setPrice( dto.getPrice());
            }if(dto.getStatus()!=null){
                existing.setStatus( dto.getStatus());
            }if(dto.getQuantity()!=null){
                existing.setQuantity( dto.getQuantity());
            }
            if(dto.getImageurl()!=null){
                existing.setImageurl( dto.getImageurl());
            }
            existing.setUpdatedAt(LocalDateTime.now());
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
            repository.findById(id).orElseThrow(()->new RuntimeException("Failed to delete product "+id));
            repository.deleteById(id);

    }

    public List<Product> getProductByCategory(String category) {
        List<Product> product= repository.findByCategoryContaining(category);
        if(product==null){
            throw new RuntimeException("Product not found");
        }
        return product;
    }
}
