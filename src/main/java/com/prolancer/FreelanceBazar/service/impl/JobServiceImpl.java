package com.prolancer.FreelanceBazar.service.impl;

import com.prolancer.FreelanceBazar.entity.JobEntity;
import com.prolancer.FreelanceBazar.entity.User;
import com.prolancer.FreelanceBazar.entity.enums.JobStatus;
import com.prolancer.FreelanceBazar.exceptions.ResourceNotFoundException;
import com.prolancer.FreelanceBazar.filter.JobFilter;
import com.prolancer.FreelanceBazar.mapper.JobMapper;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.model.BaseResponse;
import com.prolancer.FreelanceBazar.payload.request.JobCreateRequest;
import com.prolancer.FreelanceBazar.payload.request.UpdateJobRequest;
import com.prolancer.FreelanceBazar.payload.response.JobResponse;
import com.prolancer.FreelanceBazar.payload.response.SkillResponse;
import com.prolancer.FreelanceBazar.repository.JobRepository;
import com.prolancer.FreelanceBazar.repository.page.RequestPage;
import com.prolancer.FreelanceBazar.repository.page.RequestPageImpl;
import com.prolancer.FreelanceBazar.repository.page.ResponsePage;
import com.prolancer.FreelanceBazar.repository.page.ResponsePageImpl;
import com.prolancer.FreelanceBazar.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final UserSession userSession;

    @Override
    public ApiResponse getAllJobs(JobFilter filter) {

        Page<JobEntity> jobPaged = jobRepository.findAllByFilter(filter);
        List<JobResponse> responseData = jobPaged.getContent().stream()
                .map(job -> JobResponse.builder()
                        .title(job.getTitle())
                        .description(job.getDescription())
                        .skills(job.getJobSkills().stream().map(skill ->
                                SkillResponse.builder()
                                        .skillName(skill.getSkillName())
                                        .build()).toList())
                        .build()
                ).toList();

        ResponsePage<JobResponse> responsePage = new ResponsePage<>() {
            @Override
            public List<JobResponse> getElements() {
                return responseData;
            }

            @Override
            public RequestPage getRequestPage() {
                return new RequestPageImpl(filter.getPage(),
                        filter.getSize(), filter.getStart());
            }

            @Override
            public long getTotalElements() {
                return responseData.size();
            }
        };


        return ApiResponse.successResponse(responsePage, "Searched jobs");
    }

    @Override
    public ApiResponse getJobById(UUID jobId) {
        JobEntity job = jobRepository.findById(jobId).orElseThrow();
        JobResponse response = jobMapper.toResponse(job);
        return ApiResponse.successResponse(response, "Job details");
    }

    @Transactional
    @Override
    public BaseResponse<?> createJob(JobCreateRequest request, BaseResponse<?> response) {

        User user = userSession.getUser();

        JobEntity job = jobMapper.toEntity(request);
        job.setJobStatus(JobStatus.OPEN);
        job.setClient(user);
        jobRepository.save(job);


        return response;
    }

    @Transactional
    @Override
    public ApiResponse updateJob(UpdateJobRequest request) {

        JobEntity job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException("job not found"));
        jobMapper.updateEntity(job, request);
        jobRepository.save(job);

        return ApiResponse.successResponse("Job updated");
    }

    @Transactional
    @Override
    public ApiResponse deleteJob(UUID jobId) {
        jobRepository.deleteById(jobId);

        return ApiResponse.successResponse("Job deleted");
    }


}
