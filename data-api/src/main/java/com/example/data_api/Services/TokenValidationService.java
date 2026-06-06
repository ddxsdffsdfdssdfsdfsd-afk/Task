package com.example.data_api.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenValidationService {

    @Value("{internal.token}")
    private String internalToken;

    public boolean validateToken(String token){
        return internalToken.equals(token);
    }
}
