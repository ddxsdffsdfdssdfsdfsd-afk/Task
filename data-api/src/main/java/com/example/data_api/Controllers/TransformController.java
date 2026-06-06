package com.example.data_api.Controllers;

import com.example.data_api.DTOs.TransformRequest;
import com.example.data_api.Services.TokenValidationService;
import com.example.data_api.Services.TransformService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransformController {

    private final TransformService transformService;
    private final TokenValidationService tokenValidationService;

    public TransformController(TransformService transformService, TokenValidationService tokenValidationService) {
        this.transformService = transformService;
        this.tokenValidationService = tokenValidationService;
    }

    @PostMapping("/transform")
    public ResponseEntity<String> transformText(HttpServletRequest httpRequest, @RequestBody TransformRequest request) {

        String token = httpRequest.getHeader("X-Internal-Token");
        if (!tokenValidationService.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return transformService.transformText(request.getText());
    }
}
