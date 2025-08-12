package com.project.ecommerce_product_catalog.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
        private String username;
        private String password;

        // getters and setters
}
