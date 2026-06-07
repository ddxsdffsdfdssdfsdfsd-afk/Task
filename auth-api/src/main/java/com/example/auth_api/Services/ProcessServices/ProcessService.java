package com.example.auth_api.Services.ProcessServices;

import com.example.auth_api.Entyties.ProcessingLog;
import com.example.auth_api.Entyties.User;
import com.example.auth_api.Repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProcessService {
    private final ProcessTextTransform processTextTransform;
    private final UserRepository userRepository;
    private final ProcessingLogService processingLogService;

    public ProcessService(ProcessTextTransform processTextTransform, UserRepository userRepository, ProcessingLogService processingLogService) {
        this.processTextTransform = processTextTransform;
        this.userRepository = userRepository;
        this.processingLogService = processingLogService;
    }


    public ResponseEntity<String> processText(String textToTransform, String email) {

        ResponseEntity<String> transformedTextEntity = processTextTransform.transform(textToTransform);

        if (transformedTextEntity.getStatusCode().isSameCodeAs(HttpStatus.FORBIDDEN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        User user = getUserFromRequest(email);

        ProcessingLog log = new ProcessingLog();
        log.setUser(user);
        log.setInputText(textToTransform);
        log.setOutputText(transformedTextEntity.getBody());

        return processingLogService.createProcessingLog(log);
    }

    private User getUserFromRequest(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);
    }
}
