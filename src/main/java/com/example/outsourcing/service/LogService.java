package com.example.outsourcing.service;

import com.example.outsourcing.entity.LogEntity;
import com.example.outsourcing.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    private final  LogRepository logRepository;

    public LogService(LogRepository logRepository){
        this.logRepository = logRepository;
    }

    public List<LogEntity> getLog() {
        return logRepository.findAll();
    }

}
