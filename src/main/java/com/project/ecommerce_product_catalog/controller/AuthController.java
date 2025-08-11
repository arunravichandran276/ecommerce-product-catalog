package com.project.ecommerce_product_catalog.controller;


import com.project.ecommerce_product_catalog.security.JwtAuthenticationFilter;
import com.project.ecommerce_product_catalog.security.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    public AuthController(JwtUtil jwtUtil,JwtAuthenticationFilter jwtAuthenticationFilter){
        this.jwtUtil=jwtUtil;
        this.jwtAuthenticationFilter=jwtAuthenticationFilter;
    }

}
