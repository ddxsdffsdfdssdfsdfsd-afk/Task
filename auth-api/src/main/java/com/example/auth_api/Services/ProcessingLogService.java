package com.example.auth_api.Services;

import com.example.auth_api.Entyties.ProcessingLog;
import com.example.auth_api.Repositories.ProcessingLogRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProcessingLogService {

    private final ProcessingLogRepository processingLogRepository;

    public ProcessingLogService(ProcessingLogRepository processingLogRepository) {
        this.processingLogRepository = processingLogRepository;
    }

    public ResponseEntity<String> createProcessingLog(ProcessingLog log) {
        if (log.getUser() == null) return ResponseEntity.status(HttpStatus.CONFLICT).build();

        processingLogRepository.save(log);
        return ResponseEntity.ok(log.getOutputText());
    }
}
