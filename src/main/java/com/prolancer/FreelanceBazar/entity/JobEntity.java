package com.prolancer.FreelanceBazar.entity;

import com.prolancer.FreelanceBazar.entity.enums.JobStatus;
import com.prolancer.FreelanceBazar.entity.enums.JobType;
import com.prolancer.FreelanceBazar.entity.template.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@SQLDelete(sql = "update job set deleted=true where id=?")
@SQLRestriction(value = "deleted=false")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job")
@Entity
public class JobEntity extends BaseEntity {

    @Column(nullable = false)
    private String title;
    @Column(length = 5000)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;
    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;
    @Enumerated(EnumType.STRING)
    private JobType jobType;
    @ManyToOne(fetch = FetchType.LAZY)
    private User client;
    private Integer estimatedHours;
    private Double hourlyRate;
    private Double budget;

    @JoinTable(
            name = "job_skill",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Skill> jobSkills;
    @PositiveOrZero
    private Integer interests;

}
