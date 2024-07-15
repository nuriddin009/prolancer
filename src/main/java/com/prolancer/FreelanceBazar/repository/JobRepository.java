package com.prolancer.FreelanceBazar.repository;

import com.prolancer.FreelanceBazar.entity.JobEntity;
import com.prolancer.FreelanceBazar.repository.custom.JobCompositeRepository;

public interface JobRepository extends BaseRepository<JobEntity>, JobCompositeRepository {

}
