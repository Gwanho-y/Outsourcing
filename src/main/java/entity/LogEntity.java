package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
@Getter
@Setter

public class LogEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private com.example.outsourcing.entity.TaskEntity task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "name", length = 5, nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "log_content", length = 255, nullable = false)
    private String logContent;

    @Column(name = "act_type", length = 25, nullable = false)
    private String actType;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();

        if (this.task != null) {
            this.title = this.task.getTitle();
        }

        if (this.user != null) {
            this.name = this.user.getName();
        }


    }
}
