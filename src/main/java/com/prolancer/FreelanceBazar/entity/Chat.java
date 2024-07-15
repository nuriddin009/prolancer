package com.prolancer.FreelanceBazar.entity;

import com.prolancer.FreelanceBazar.entity.enums.MessageType;
import com.prolancer.FreelanceBazar.entity.template.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Setter
@Builder
@SQLDelete(sql = "update chat set deleted=true where id=?")
@SQLRestriction(value = "deleted=false")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chat extends BaseEntity {
    @ManyToOne
    private User sender;
    @ManyToOne
    private User receiver;
    @Enumerated(EnumType.STRING)
    private MessageType messageType;
}
