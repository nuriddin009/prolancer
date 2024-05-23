package com.prolancer.FreelanceBazar.entity;

import com.prolancer.FreelanceBazar.entity.template.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Builder
@SQLDelete(sql = "update company set deleted=true where id=?")
@SQLRestriction(value = "deleted=false")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company extends BaseEntity {

    private String companyName;
    private String location;


}
