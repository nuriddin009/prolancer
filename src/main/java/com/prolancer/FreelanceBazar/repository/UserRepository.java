package com.prolancer.FreelanceBazar.repository;

import com.prolancer.FreelanceBazar.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByEmail(String username);
}
