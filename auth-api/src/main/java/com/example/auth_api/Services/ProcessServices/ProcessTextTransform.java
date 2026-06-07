package com.example.auth_api.Services.ProcessServices;

import com.example.auth_api.DTOs.ProcessDTOs.TransformRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ProcessTextTransform {

    @Value("{internal.token}")
    private String internalToken;

    private final RestClient restClient;

    public ProcessTextTransform(RestClient restClient) {
        this.restClient = restClient;
    }

    public ResponseEntity<String> transform(String text) {

     return restClient.post()
                .uri("http://data-api:8081/api/transform")
                .header("X-Internal-Token", internalToken)
                .body(new TransformRequest(text))
                .retrieve()
                .toEntity(String.class);
    }
}
