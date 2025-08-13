package com.project.ecommerce_product_catalog.service;

import com.project.ecommerce_product_catalog.dto.UserDTO;
import com.project.ecommerce_product_catalog.model.User;
import com.project.ecommerce_product_catalog.repository.UserRepository;
import com.project.ecommerce_product_catalog.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // CRUD
    public Page<User> showAllUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    public User getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Failed to get user details"));
    }

    public void updateUser(Long id, UserDTO user) {
        User existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Failed to update user details"));

        if(user.getUsername() != null) existing.setUsername(user.getUsername());
        if(user.getPassword() != null) existing.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRole() != null) existing.setRole(user.getRole());

        repository.save(existing);
    }

    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    public User getUserByName(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void updateProfile(User user) {
        repository.save(user);
    }

    // Authentication
    public void signup(String username, String password) {
        if (repository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        repository.save(user);
    }
    public HashMap<String,String> login(String username, String password) {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return jwtUtil.generateToken(username);
    }
}

