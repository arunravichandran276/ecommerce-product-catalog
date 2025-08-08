package com.project.ecommerce_product_catalog.service;

import com.project.ecommerce_product_catalog.dto.UserDTO;
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

    public User getUserById(Long id) {
            return repository.findById(id).
                    orElseThrow(()-> new RuntimeException("Failed to get user details"));
    }
    public void updateUser(Long id,UserDTO user) {
        try{
            User existing=repository.findById(id).orElseThrow(()->new RuntimeException("Failed to update user details"));
            if(user.getUsername()!=null){
                existing.setUsername(user.getUsername());
            }
            if(user.getPassword()!=null){
                existing.setPassword(user.getPassword());
            }
            if(user.getRole()!=null){
                existing.setRole(user.getRole());
            }
            repository.save(existing);
        }
        catch (Exception e){
            throw new RuntimeException("failed to update user details");
        }
    }

    public void deleteUser(Long id) {
        try{
            repository.deleteById(id);
        }
        catch (Exception e){
            throw new RuntimeException("Failed to delete user "+e.getMessage());
        }
    }

    public User getUserByName(String username) {
        try{
            return repository.findByUsername(username);
        }
        catch (Exception e){
            throw new RuntimeException("failed to get the your user details "+e.getMessage());
        }
    }
    public void updateProfile(User user) {
        try{
             repository.save(user);
        }
        catch (Exception e){
            throw new RuntimeException("failed to update profile "+e.getMessage());
        }
    }
}
