package com.base.restassured;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String type;
    private String username;
    private long expiresIn;
}
