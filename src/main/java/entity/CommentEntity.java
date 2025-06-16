package entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import org.springframework.scheduling.config.Task;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class CommentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    private TaskEntity taskId;

    @ManyToOne(optional = false)
    private UserEntity user;

    @Column(nullable=false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable=false)
    private LocalDateTime createdAt;

    @Column(nullable=false)
    private LocalDateTime updatedAt;

    @PrePersist void onCreate(){createdAt=LocalDateTime.now();}
    @PreUpdate void onUpdate(){updatedAt=LocalDateTime.now();}
    // getters and setters
}
