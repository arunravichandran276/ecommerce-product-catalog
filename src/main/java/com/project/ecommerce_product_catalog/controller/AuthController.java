package com.project.ecommerce_product_catalog.controller;


import com.project.ecommerce_product_catalog.dto.AuthRequest;
import com.project.ecommerce_product_catalog.dto.LoginResponse;
import com.project.ecommerce_product_catalog.security.JwtAuthenticationFilter;
import com.project.ecommerce_product_catalog.security.JwtUtil;
import com.project.ecommerce_product_catalog.service.UserService;
//import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.project.ecommerce_product_catalog.dto.ApiResponse;

import java.util.HashMap;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    public AuthController(JwtUtil jwtUtil,JwtAuthenticationFilter jwtAuthenticationFilter){
        this.jwtUtil=jwtUtil;
        this.jwtAuthenticationFilter=jwtAuthenticationFilter;
    }
    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody AuthRequest request) {
        try {
            service.signup(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(new ApiResponse("User registered successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("Registration failed: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
//            HashMap<String ,String > map=new HashMap<>();
            String accesstoken = service.login(request.getUsername(), request.getPassword()).get("ACCESSTOKEN");
            String refreshtoken = service.login(request.getUsername(), request.getPassword()).get("REFRESHTOKEN");
//            map.put("ACCESSTOKEN:",accesstoken);
//            map.put("REFRESHTOKEN",refreshtoken);
            return ResponseEntity.ok(new LoginResponse("Login successful",accesstoken,refreshtoken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse("Login failed: " + e.getMessage()));
        }
    }
//    @PostMapping("/refresh")
//    public ResponseEntity<?> validaterefreshtoken(@RequestBody String username,@RequestBody String refreshtoken){
//        try{
//            service.validateRefreshToken(username,refreshtoken);
//            return
//        }
//    }

}
