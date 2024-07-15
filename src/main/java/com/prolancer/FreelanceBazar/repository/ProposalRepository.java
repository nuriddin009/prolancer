package com.prolancer.FreelanceBazar.repository;

import com.prolancer.FreelanceBazar.entity.JobProposal;

import java.util.List;
import java.util.UUID;

public interface ProposalRepository extends BaseRepository<JobProposal> {

    List<JobProposal> findAllByFreelancerId(UUID freelancerId);
    List<JobProposal> findAllByJobId(UUID jobId);

}
