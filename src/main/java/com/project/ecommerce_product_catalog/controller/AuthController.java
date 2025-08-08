package com.project.ecommerce_product_catalog.controller;

import com.project.ecommerce_product_catalog.dto.UserDTO;
import com.project.ecommerce_product_catalog.model.User;
import com.project.ecommerce_product_catalog.security.CustomUserDetails;
import com.project.ecommerce_product_catalog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthController {
    @Autowired
    UserService service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/allusers")
    public Page<User> showAllUsers(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "5") int size,
                                   @RequestParam(defaultValue = "username") String sortBy){
        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));
        Page<User> users= service.showAllUsers(pageable);
        return users;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        try{
            User user=service.getUserById(id);
            return new ResponseEntity<>(user,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/me")
    public ResponseEntity<User> getMyProfile(Authentication authentication){
        try{
           String username=authentication.getName();
           User user=service.getUserByName(username);
            return new ResponseEntity<>(user,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/updateuser/{id}")
    public ResponseEntity<String> updateProfile(@PathVariable Long id,@RequestBody UserDTO user){
        try{
            service.updateUser(id,user);
            return new ResponseEntity<>("User Updated Successfully",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to update user details"+e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @PatchMapping("/updatprofile")
    public ResponseEntity<String> updateProfile(Authentication authentication){
        try{
            String username=authentication.getName();
            User user=service.getUserByName(username);
            service.updateProfile(user);
            return new ResponseEntity<>("User Updated Successfully",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to update your profile"+e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
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
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/updateuser/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody UserDTO user){
        try{
            service.updateUser(id,user);
            return new ResponseEntity<>("User Updated Successfully",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to update user details"+e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        try{
            service.deleteUser(id);
            return new ResponseEntity<>("User Deleted Successfully",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Failed to delete the user "+e.getMessage(),HttpStatus.CONFLICT);
        }

    }


}
