package com.example.outsourcing.controller;
import com.example.outsourcing.entity.LogEntity;
import com.example.outsourcing.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/logs")
    public List<LogEntity> getLog() {
        return logService.getLog();
    }
}
