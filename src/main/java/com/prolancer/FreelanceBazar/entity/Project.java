package com.prolancer.FreelanceBazar.entity;

import com.prolancer.FreelanceBazar.entity.template.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    @PositiveOrZero
    private BigDecimal budget;
    private LocalDateTime deadline;
    @JoinColumn(name = "client_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User client;
    @ManyToOne(fetch = FetchType.LAZY)
    private Freelancer freelance;
}
