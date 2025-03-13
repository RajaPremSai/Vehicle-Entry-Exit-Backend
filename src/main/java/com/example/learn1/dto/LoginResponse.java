package com.example.learn1.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String message;
    private String role;
    private String token;
    private String email;

    public LoginResponse(String message, String role, String token) {
        this.message = message;
        this.role = role;
        this.token = token;
        this.email = email;
    }

    // Getters and Setters
}
