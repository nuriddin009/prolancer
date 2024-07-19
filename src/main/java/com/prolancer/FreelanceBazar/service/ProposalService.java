package com.prolancer.FreelanceBazar.service;

import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.model.BaseResponse;
import com.prolancer.FreelanceBazar.payload.request.ProposalRequest;
import com.prolancer.FreelanceBazar.payload.request.UpdateProposalRequest;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface ProposalService {
    ApiResponse getProposalByJobId(UUID jobId);

    ApiResponse getFreelancerProposal(UUID freelancerId);

    BaseResponse<?> createProposal(ProposalRequest request, BaseResponse<?> response);

    ResponseEntity<?> changeProposalStatus(UUID proposalId, UpdateProposalRequest request);
}
