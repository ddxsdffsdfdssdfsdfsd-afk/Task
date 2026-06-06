package com.example.auth_api.DTOs;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
