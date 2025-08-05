package com.project.ecommerce_product_catalog.controller;

import com.project.ecommerce_product_catalog.model.Product;
import com.project.ecommerce_product_catalog.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService service;
    @GetMapping("/")
    public  String home(){
        return "Welcome  to my website";
    }
    @GetMapping("/allproducts")
    public ResponseEntity<List<Product>> showProducts(){
        List<Product> products= service.showProducts();
        return new ResponseEntity<>(products, HttpStatus.FOUND);
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id){
        return service.getProductById(id);
    }
    @PostMapping("/addproduct")
    public void addProduct(Product product){
        service.addProduct(product);
    }
    @PutMapping("update/{id}")
    public void updateProduct(Product product,@PathVariable int id){
        service.updateProduct(id,product);
    }
    @DeleteMapping("")
    public void deleteProduct(int id){
        service.deleteProduct(id);
    }

    

}
