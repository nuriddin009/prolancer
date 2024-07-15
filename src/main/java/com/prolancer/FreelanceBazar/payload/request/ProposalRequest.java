package com.prolancer.FreelanceBazar.payload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ProposalRequest {
    @NotNull
    private UUID jobId;
    @NotEmpty
    private List<UUID> skillIds;
}
