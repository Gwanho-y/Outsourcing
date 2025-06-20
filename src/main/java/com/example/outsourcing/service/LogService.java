// LogService.java
package com.example.outsourcing.service;

import com.example.outsourcing.dto.log.LogResponserDto;
import com.example.outsourcing.entity.LogEntity;
import com.example.outsourcing.global.exception.LogNotFoundException;
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
        List<LogEntity> logbyuserid = logRepository.findAllByUser_Id(userId);
        if (logbyuserid.isEmpty()) {
            throw new LogNotFoundException("userId: " + userId + "에 해당하는 로그가 없습니다.");
        }
        return logRepository.findAllByUser_Id(userId).stream()
                .map(LogResponserDto::toDto)
                .toList();
    }

    public List<LogResponserDto> getLogsByName(String name) {
        List<LogEntity> logbyname = logRepository.findAllByName(name);
        if (logbyname.isEmpty()) {
            throw new LogNotFoundException(name + "에 해당하는 로그가 없습니다.");
        }
        return logRepository.findAllByName(name).stream()
                .map(LogResponserDto::toDto)
                .toList();
    }

    public List<LogResponserDto> getLogsByActType(String actType) {
        List<LogEntity> logbyacttype = logRepository.findAllByActType(actType);
        if (logbyacttype.isEmpty()) {
            throw new LogNotFoundException("actType: " + actType + "에 해당하는 로그가 없습니다.");
        }
        return logRepository.findAllByActType(actType).stream()
                .map(LogResponserDto::toDto)
                .toList();
    }
}
