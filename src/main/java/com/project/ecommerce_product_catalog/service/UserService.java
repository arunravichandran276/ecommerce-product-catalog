package com.project.ecommerce_product_catalog.service;

import com.project.ecommerce_product_catalog.model.User;
import com.project.ecommerce_product_catalog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public Page<User> showAllUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void addUser(User user) {
        try{
            repository.save(user);
        }
        catch (Exception e){
            throw new RuntimeException("failed to create a user"+e.getMessage());
        }
    }
}
