// LogService.java
package com.example.outsourcing.service;

import com.example.outsourcing.dto.log.LogResponserDto;
import com.example.outsourcing.entity.LogEntity;
import com.example.outsourcing.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    private final LogRepository logRepository;

    public LogService(LogRepository logRepository){
        this.logRepository = logRepository;
    }

    public List<LogResponserDto> getLog() {
        return logRepository.findAll().stream()
                .map(LogResponserDto::toDto)
                .toList();
    }

    public List<LogResponserDto> getLogsByUserId(Long userId) {
        return logRepository.findAllByUser_UserId(userId).stream()
                .map(LogResponserDto::toDto)
                .toList();
    }

    public List<LogResponserDto> getLogsByName(String name) {
        return logRepository.findAllByName(name).stream()
                .map(LogResponserDto::toDto)
                .toList();
    }

    public List<LogResponserDto> getLogsByActType(String actType) {
        return logRepository.findAllByActType(actType).stream()
                .map(LogResponserDto::toDto)
                .toList();
    }
}
