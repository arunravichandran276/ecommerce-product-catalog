package com.project.ecommerce_product_catalog.dto;

import lombok.*;


@Getter
@Setter
public class LoginResponse extends ApiResponse {
    private String token;

    public LoginResponse(String message, String token) {
        super(message);
        this.token = token;
    }
    public String getToken() { return token; }

    public void setToken(String token) {
        this.token = token;
    }
}
