package com.prolancer.FreelanceBazar.controller;

import com.prolancer.FreelanceBazar.filter.JobFilter;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.model.BaseResponse;
import com.prolancer.FreelanceBazar.payload.request.JobCreateRequest;
import com.prolancer.FreelanceBazar.payload.request.UpdateJobRequest;
import com.prolancer.FreelanceBazar.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/job")
public class JobController {

    private final JobService jobService;

    /* TODO:
         get all jobs
         getJobById
         createJob
         updateJOb
         deleteJob
     */


    @GetMapping
    public HttpEntity<ApiResponse> getAll(@ParameterObject JobFilter filter) {
        return ResponseEntity.ok(jobService.getAllJobs(filter));
    }

    @GetMapping("/byId")
    public HttpEntity<ApiResponse> getJobById(@RequestParam UUID jobId) {
        return ResponseEntity.ok(jobService.getJobById(jobId));
    }

    @PostMapping
    public HttpEntity<BaseResponse<?>> createJob(@RequestBody JobCreateRequest request) {
        BaseResponse<?> response = new BaseResponse<>();
        response = jobService.createJob(request, response);
        HttpStatus status = response.getSuccess() ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(response);
    }

    @PutMapping
    public HttpEntity<ApiResponse> updateJob(@RequestBody UpdateJobRequest request) {
        return ResponseEntity.ok(jobService.updateJob(request));
    }

    @DeleteMapping
    public HttpEntity<ApiResponse> deleteJob(@RequestParam UUID jobId) {
        return ResponseEntity.ok(jobService.deleteJob(jobId));
    }
}
