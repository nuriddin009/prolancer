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
@SQLDelete(sql = "update proposal set deleted=true where id=?")
@SQLRestriction(value = "deleted=false")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Proposal extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    private Freelancer freelancer;
    @Positive
    private BigDecimal paymentAmount;
    @Enumerated(EnumType.STRING)
    private ProposalStatus proposalStatus;
    @Column(columnDefinition = "text")
    private String clientComment;

}
