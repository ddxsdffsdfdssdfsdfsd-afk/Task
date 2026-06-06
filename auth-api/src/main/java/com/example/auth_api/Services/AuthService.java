package com.example.auth_api.Services;

import com.example.auth_api.DTOs.LoginRequest;
import com.example.auth_api.DTOs.RegisterRequest;
import com.example.auth_api.DTOs.TokenResponse;
import com.example.auth_api.Entyties.User;
import com.example.auth_api.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public ResponseEntity<Void> register(RegisterRequest request) {

        Boolean isEmailEmpty = request.getEmail().trim().isEmpty() || request.getPassword().trim().isEmpty();
        if (isEmailEmpty) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        if (isUserExistsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .build();
        }

        User user = new User();

        user.setEmail(request.getEmail());
        user.setPasswordHash(
                passwordEncoder.encode(request.getPassword())
        );

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    private Boolean isUserExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }



    public ResponseEntity<TokenResponse> login(LoginRequest request) {

        if (request.getEmail() == null || request.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        User user = getUserByEmail(request.getEmail());

        Boolean isUserAndPasswordValid = user != null && passwordEncoder.matches(request.getPassword(), user.getPasswordHash());

        if (!isUserAndPasswordValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

        String token = jwtService.generateToken(user.getEmail());

        return ResponseEntity.ok(new TokenResponse(token));
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);
    }

}