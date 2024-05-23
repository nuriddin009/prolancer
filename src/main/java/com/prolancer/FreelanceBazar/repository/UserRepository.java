package com.prolancer.FreelanceBazar.repository;

import com.prolancer.FreelanceBazar.entity.User;
import com.prolancer.FreelanceBazar.repository.custom.UserCompositeRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User>, UserCompositeRepository {
    Optional<User> findByEmail(String username);
}
