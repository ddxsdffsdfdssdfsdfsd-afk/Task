package com.example.data_api.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransformService {

    public ResponseEntity<String> transformText(String text){
        String transformedText = text.toUpperCase();

        return ResponseEntity.ok(transformedText);
    }
}
