package com.example.auth_api.Controllers;

import com.example.auth_api.DTOs.ProcessRequest;
import com.example.auth_api.Services.ProcessService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProcessController {

    private final ProcessService processService;

    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    @PostMapping("/process")
    public ResponseEntity<String> precessRequest(Authentication authentication, @RequestBody ProcessRequest request) {
        return processService.processText(request.getText(), authentication.getName());
    }
}