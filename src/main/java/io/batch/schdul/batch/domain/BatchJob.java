package io.batch.schdul.batch.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.batch.core.BatchStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class BatchJob {

    @Id
    @Column(nullable = false, columnDefinition = "varbinary(128)")
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    @Enumerated(EnumType.STRING)
    private BatchStatus batchStatus;

    @Column(updatable = false)
    private LocalDateTime create_at;

    private LocalDateTime update_at;

    @PrePersist
    protected void onCreate() {
        create_at = LocalDateTime.now();
        update_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        update_at = LocalDateTime.now();
    }
}
