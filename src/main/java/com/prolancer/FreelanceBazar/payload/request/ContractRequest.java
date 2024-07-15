package com.prolancer.FreelanceBazar.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ContractRequest {
    @NotNull
    private UUID clientId;
    @NotNull
    private UUID jobId;
    private Date startDate;
    private Date endDate;
}
