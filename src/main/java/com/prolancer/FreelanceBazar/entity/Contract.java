package com.prolancer.FreelanceBazar.entity;

import com.prolancer.FreelanceBazar.entity.template.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update contract set deleted=true where id=?")
@SQLRestriction(value = "deleted=false")
@Entity
public class Contract extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Freelancer freelancer;

}
