package com.prolancer.FreelanceBazar.service;

import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.model.BaseResponse;
import com.prolancer.FreelanceBazar.payload.request.ProposalRequest;

import java.util.UUID;

public interface ProposalService {
    ApiResponse getProposalByJobId(UUID jobId);

    ApiResponse getFreelancerProposal(UUID freelancerId);

    BaseResponse<?> createProposal(ProposalRequest request, BaseResponse<?> response);
}
