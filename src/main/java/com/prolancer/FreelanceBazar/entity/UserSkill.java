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
@SQLDelete(sql = "update user_skill set deleted=true where id=?")
@SQLRestriction(value = "deleted=false")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserSkill extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Skill skill;
}
