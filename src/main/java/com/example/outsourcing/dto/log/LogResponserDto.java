package com.example.outsourcing.dto.log;

import com.example.outsourcing.entity.LogEntity;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
public class LogResponserDto {
    private Long logId;
    private String name;
    private String actType;
    private String logContent;
    private LocalDateTime createdAt;

    public LogResponserDto(LogEntity log) {
        this.logId = log.getLogId();
        this.name = log.getName();
        this.actType = log.getActType();
        this.logContent = log.getLogContent();
        this.createdAt = log.getCreatedAt();
    }

    public static LogResponserDto toDto(LogEntity log) {
        return new LogResponserDto(
                log.getLogId(),
                log.getName(),
                log.getActType(),
                log.getLogContent(),
                log.getCreatedAt()
        );
    }
}
