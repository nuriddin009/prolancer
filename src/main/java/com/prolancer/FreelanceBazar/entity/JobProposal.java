package com.prolancer.FreelanceBazar.entity;

import com.prolancer.FreelanceBazar.entity.enums.ProposalStatus;
import com.prolancer.FreelanceBazar.entity.template.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@SQLDelete(sql = "update proposal set deleted=true where id=?")
@SQLRestriction(value = "deleted=false")
@Builder
@Table(name = "proposal")
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
    @Column(columnDefinition = "text")
    private String coverLetter;
    @Column(nullable = false)
    private Double proposedRate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "proposal_skill",
            joinColumns = @JoinColumn(name = "proposal_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;

}
