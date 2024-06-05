package com.prolancer.FreelanceBazar.payload.request;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateJobRequest {
    private UUID jobId;
}
