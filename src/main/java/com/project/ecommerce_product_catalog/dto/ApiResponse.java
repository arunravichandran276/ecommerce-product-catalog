package com.project.ecommerce_product_catalog.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
class ApiResponse {
    private String message;

    public ApiResponse(String message) { this.message = message; }
    public String getMessage() { return message; }
}
