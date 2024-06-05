package com.prolancer.FreelanceBazar.repository.custom;

import com.prolancer.FreelanceBazar.entity.JobEntity;
import com.prolancer.FreelanceBazar.filter.JobFilter;
import org.springframework.data.domain.Page;

public interface JobCompositeRepository {
    Page<JobEntity> findAllByFilter(JobFilter filter);
}
