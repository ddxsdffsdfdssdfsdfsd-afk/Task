package com.example.auth_api.Controllers;

import com.example.auth_api.DTOs.AuthDTOs.LoginRequest;
import com.example.auth_api.DTOs.AuthDTOs.RegisterRequest;
import com.example.auth_api.DTOs.TokenDTOs.TokenResponse;
import com.example.auth_api.Services.AuthService.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
