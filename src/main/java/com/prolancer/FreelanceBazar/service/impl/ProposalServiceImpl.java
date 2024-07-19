package com.prolancer.FreelanceBazar.service.impl;

import com.prolancer.FreelanceBazar.entity.JobEntity;
import com.prolancer.FreelanceBazar.entity.JobProposal;
import com.prolancer.FreelanceBazar.entity.Skill;
import com.prolancer.FreelanceBazar.entity.User;
import com.prolancer.FreelanceBazar.exceptions.ResourceNotFoundException;
import com.prolancer.FreelanceBazar.mapper.JobProposalMapper;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.model.BaseResponse;
import com.prolancer.FreelanceBazar.payload.request.ProposalRequest;
import com.prolancer.FreelanceBazar.payload.request.UpdateProposalRequest;
import com.prolancer.FreelanceBazar.payload.response.JobProposalResponse;
import com.prolancer.FreelanceBazar.repository.JobRepository;
import com.prolancer.FreelanceBazar.repository.ProposalRepository;
import com.prolancer.FreelanceBazar.repository.SkillRepository;
import com.prolancer.FreelanceBazar.service.ProposalService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProposalServiceImpl implements ProposalService {
    private final ProposalRepository proposalRepository;
    private final UserSession userSession;
    private final JobRepository jobRepository;
    private final JobProposalMapper proposalMapper;
    private final SkillRepository skillRepository;

    @Override
    public ApiResponse getProposalByJobId(UUID jobId) {
        List<JobProposal> jobProposalList = proposalRepository.findAllByJobId(jobId);
        List<JobProposalResponse> resList = proposalMapper.toResList(jobProposalList);
        return new ApiResponse(resList, true);
    }

    @Override
    public ApiResponse getFreelancerProposal(UUID freelancerId) {
        List<JobProposal> data = proposalRepository.findAllByFreelancerId(freelancerId);
        return ApiResponse.successResponse(data, "ok");
    }

    @Transactional
    @Override
    public BaseResponse<?> createProposal(ProposalRequest request, BaseResponse<?> response) {

        User user = userSession.getUser();



        JobEntity job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new EntityNotFoundException("Job not found for this id -> " + request.getJobId()));

        JobProposal jobProposal = proposalMapper.toEntity(request);
        jobProposal.setJob(job);

        List<Skill> skills = request.getSkillIds().stream()
                .map(skillId -> skillRepository.findById(skillId)
                        .orElseThrow(() ->
                                new EntityNotFoundException("Skill not found for this id -> " + skillId))).toList();
        jobProposal.setSkills(skills);
        proposalRepository.save(jobProposal);

        return response;
    }

    @Override
    public ResponseEntity<?> changeProposalStatus(UUID proposalId, UpdateProposalRequest request) {

        JobProposal jobProposal = proposalRepository.findById(proposalId).orElseThrow();
        jobProposal.setProposalStatus(request.getStatus());
        proposalRepository.save(jobProposal);






        return ResponseEntity.noContent().build();
    }
}
