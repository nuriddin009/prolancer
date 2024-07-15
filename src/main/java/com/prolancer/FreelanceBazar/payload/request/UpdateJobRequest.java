package com.prolancer.FreelanceBazar.payload.request;

import com.prolancer.FreelanceBazar.entity.enums.JobStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateJobRequest {
    @NotNull
    private UUID jobId;
    private JobStatus status;
}
