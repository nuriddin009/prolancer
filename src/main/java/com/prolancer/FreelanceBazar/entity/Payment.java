package com.prolancer.FreelanceBazar.entity;

import com.prolancer.FreelanceBazar.entity.enums.PayType;
import com.prolancer.FreelanceBazar.entity.template.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update payment set deleted=true where id=?")
@SQLRestriction(value = "deleted=false")
@Entity
public class Payment extends BaseEntity {

    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PayType payType;

}
