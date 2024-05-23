package com.prolancer.FreelanceBazar.entity;

import com.prolancer.FreelanceBazar.entity.template.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update freelancer set deleted=true where id=?")
@SQLRestriction(value = "deleted=false")
@Entity
public class Freelancer extends BaseEntity {

    @JoinColumn(name = "user_account_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userAccount;

    private LocalDateTime registrationDate;

}
