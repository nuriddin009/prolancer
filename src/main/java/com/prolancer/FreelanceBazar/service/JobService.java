package com.prolancer.FreelanceBazar.service;

import com.prolancer.FreelanceBazar.filter.JobFilter;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.model.BaseResponse;
import com.prolancer.FreelanceBazar.payload.request.JobCreateRequest;
import com.prolancer.FreelanceBazar.payload.request.UpdateJobRequest;

import java.util.UUID;

public interface JobService {
    ApiResponse getAllJobs(JobFilter filter);

    ApiResponse getJobById(UUID jobId);

    BaseResponse<?> createJob(JobCreateRequest request, BaseResponse<?> response);

    ApiResponse updateJob(UpdateJobRequest request);

    ApiResponse deleteJob(UUID jobId);
}
