package com.prolancer.FreelanceBazar.controller;

import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.model.BaseResponse;
import com.prolancer.FreelanceBazar.payload.request.ProposalRequest;
import com.prolancer.FreelanceBazar.payload.request.UpdateProposalRequest;
import com.prolancer.FreelanceBazar.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/proposal")
public class ProposalController {
    private final ProposalService service;


    @GetMapping
    public ResponseEntity<ApiResponse> getProposalByJobId(@RequestParam UUID jobId) {
        return ResponseEntity.ok(service.getProposalByJobId(jobId));
    }

    @GetMapping("freelancer")
    public ResponseEntity<ApiResponse> getProposalByFreelancer(@RequestParam UUID freelancerId) {
        return ResponseEntity.ok(service.getFreelancerProposal(freelancerId));
    }

    @PostMapping
    public ResponseEntity<BaseResponse<?>> createProposal(@RequestBody ProposalRequest request) {
        BaseResponse<?> response = new BaseResponse<>();
        response = service.createProposal(request, response);
        HttpStatus status = response.getSuccess() ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(response, status);
    }

    @PatchMapping("{proposalId}")
    public ResponseEntity<?> changeProposalStatus(@PathVariable UUID proposalId, @RequestBody UpdateProposalRequest request) {
        return service.changeProposalStatus(proposalId, request);
    }
}
