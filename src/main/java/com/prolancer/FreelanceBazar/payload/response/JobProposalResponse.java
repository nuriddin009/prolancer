package com.prolancer.FreelanceBazar.payload.response;

import com.prolancer.FreelanceBazar.entity.enums.ProposalStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class JobProposalResponse {
    private UUID proposalId;
    private UUID jobId;
    private JobResponse job;
    private ProposalStatus status;
    private UserResponse freelancer;
    private String coverLetter;

}
