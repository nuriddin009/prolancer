package com.prolancer.FreelanceBazar.entity;

import com.prolancer.FreelanceBazar.entity.enums.ProposalStatus;
import com.prolancer.FreelanceBazar.entity.template.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;

@Getter
@Setter
@SQLDelete(sql = "update job_proposal set deleted=true where id=?")
@SQLRestriction(value = "deleted=false")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobProposal extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "freelancer_id", nullable = false)
    private User freelancer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private JobEntity job;
    @Enumerated(EnumType.STRING)
    private ProposalStatus proposalStatus;
    @Column(length = 5000)
    private String coverLetter;
    @Column(nullable = false)
    private Double proposedRate;
}
