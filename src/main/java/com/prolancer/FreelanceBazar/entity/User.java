package com.prolancer.FreelanceBazar.entity;

import com.prolancer.FreelanceBazar.entity.enums.Status;
import com.prolancer.FreelanceBazar.entity.enums.Variables;
import com.prolancer.FreelanceBazar.entity.template.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Getter
@Setter
@Builder
@SQLDelete(sql = "update users set deleted=true where id=?")
@SQLRestriction(value = "deleted=false")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User extends BaseEntity {

    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    private String phoneNumber;
    @Column(unique = true)
    @Email(regexp = Variables.emailRegex, message = "Invalid email")
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String code;
    @Column(columnDefinition = "boolean default false")
    private boolean forgotPassword;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public List<String> toRoleList() {
        return this.roles.stream().map(Object::toString).toList();
    }

}
