package com.prolancer.FreelanceBazar.repository;

import com.prolancer.FreelanceBazar.entity.Role;
import com.prolancer.FreelanceBazar.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(RoleName roleName);
}
