package com.prolancer.FreelanceBazar.service;

import com.prolancer.FreelanceBazar.entity.enums.ContractStatus;
import com.prolancer.FreelanceBazar.filter.ContractFilter;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.ContractRequest;

import java.util.UUID;

public interface ContractService {
    ApiResponse getAllContracts(ContractFilter filter);

    ApiResponse getContractById(UUID contractId);

    ApiResponse createContract(ContractRequest request);

    ApiResponse updateContractStatus(UUID contractId, ContractStatus status);

    ApiResponse deleteContract(UUID contractId);
}
