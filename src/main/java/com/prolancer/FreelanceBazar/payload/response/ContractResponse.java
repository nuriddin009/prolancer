package com.prolancer.FreelanceBazar.payload.response;

import com.prolancer.FreelanceBazar.entity.enums.ContractStatus;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ContractResponse {
    private UUID contractId;
    private JobResponse job;
    private UserResponse freelancer;
    private UserResponse client;
    private Double agreedRate;
    private ContractStatus status;
    private Date startDate;
    private Date endDate;
}
