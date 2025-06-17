// LogController.java
package com.example.outsourcing.controller;

import com.example.outsourcing.dto.log.LogResponserDto;
import com.example.outsourcing.service.LogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    // 전체 로그 조회
    @GetMapping
    public List<LogResponserDto> getLog() {
        return logService.getLog();
    }

    @GetMapping("/user/{userId}")
    public List<LogResponserDto> getLogsByUserId(@PathVariable Long userId) {
        return logService.getLogsByUserId(userId);
    }

    @GetMapping("/{name}")
    public List<LogResponserDto> getLogsByName(@PathVariable String name) {
        return logService.getLogsByName(name);
    }

    @GetMapping("/{actType}")
    public List<LogResponserDto> getLogsByActType(@PathVariable String actType) {
        return logService.getLogsByActType(actType);
    }
}
