package com.example.auth_api.DTOs.AuthDTOs;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
