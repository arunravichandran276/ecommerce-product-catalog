package com.project.ecommerce_product_catalog.controller;

import com.project.ecommerce_product_catalog.model.User;
import com.project.ecommerce_product_catalog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")

public class AuthController {
    @Autowired
    UserService service;
    @GetMapping("/allusers")
    public Page<User> showAllUsers(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size,
                                   @RequestParam(defaultValue = "username") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        Page<User> users=service.showAllUsers(pageable);
        return users;
    }
    @PostMapping("/adduser")
    public ResponseEntity<String> addUser(@RequestBody User user){
        try{

            service.addUser(user);
            return  new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to create a user "+e.getMessage(),HttpStatus.CONFLICT);
        }
    }


}
