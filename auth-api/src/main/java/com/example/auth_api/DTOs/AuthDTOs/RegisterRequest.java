package com.example.auth_api.DTOs.AuthDTOs;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
}
