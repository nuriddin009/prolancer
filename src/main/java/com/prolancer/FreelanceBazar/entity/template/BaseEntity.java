package com.prolancer.FreelanceBazar.entity.template;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.prolancer.FreelanceBazar.entity.audit.UserAudit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity extends UserAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean deleted;
}
