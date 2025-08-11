package com.project.ecommerce_product_catalog.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public abstract class JwtAuthenticationFilter extends OncePerRequestFilter {
    
}
