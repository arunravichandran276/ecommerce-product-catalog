package com.project.ecommerce_product_catalog.controller;


import com.project.ecommerce_product_catalog.model.Product;
import com.project.ecommerce_product_catalog.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private OrderService service;
    public void OrderController(OrderService service){
        this.service=service;
    }
    @GetMapping("/{orderid}")
    public ResponseEntity<?> getorders(@PathVariable  int orderid){
        try{
            List<Product> products=service.getOrders(orderid);
            return new ResponseEntity<>("Orders fetched successfully", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("failed to get products "+e.getMessage(),HttpStatus.CONFLICT);
        }
    }


}
