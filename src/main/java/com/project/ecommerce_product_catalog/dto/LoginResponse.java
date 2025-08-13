package com.project.ecommerce_product_catalog.dto;

import lombok.*;


@Getter
@Setter
public class LoginResponse extends ApiResponse {
    private String accesstoken;
    private String refreshtoken;

    public LoginResponse(String message, String accesstoken,String refreshtoken) {
        super(message);
        this.accesstoken = accesstoken;
        this.refreshtoken=refreshtoken;
    }
    public String getaccesstoken() { return accesstoken; }

    public void setaccesstoken(String token) {
        this.accesstoken = accesstoken;
    }

    public String getRefreshtoken() {
        return refreshtoken;
    }

    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }
}
