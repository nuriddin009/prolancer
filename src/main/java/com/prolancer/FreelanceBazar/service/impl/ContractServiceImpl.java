package com.prolancer.FreelanceBazar.service.impl;

import com.prolancer.FreelanceBazar.entity.Contract;
import com.prolancer.FreelanceBazar.entity.enums.ContractStatus;
import com.prolancer.FreelanceBazar.exceptions.ResourceNotFoundException;
import com.prolancer.FreelanceBazar.filter.ContractFilter;
import com.prolancer.FreelanceBazar.mapper.ContractMapper;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.ContractRequest;
import com.prolancer.FreelanceBazar.payload.response.ContractResponse;
import com.prolancer.FreelanceBazar.repository.ContractRepository;
import com.prolancer.FreelanceBazar.service.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final ContractMapper contractMapper;

    @Override
    public ApiResponse getAllContracts(ContractFilter filter) {


        return null;
    }

    @Override
    public ApiResponse getContractById(UUID contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow();
        ContractResponse response = contractMapper.toResponse(contract);
        return ApiResponse.successResponse("", "Contract");
    }

    @Transactional
    @Override
    public ApiResponse createContract(ContractRequest request) {
        Contract contract = contractMapper.toEntity(request);
        contractRepository.save(contract);
        return ApiResponse.successResponse("Contract created");
    }

    @Transactional
    @Override
    public ApiResponse updateContractStatus(UUID contractId, ContractStatus status) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract not found for this id -> " + contractId));
        contract.setStatus(status);

        if (status == ContractStatus.COMPLETED || status == ContractStatus.CANCELLED) {
            contract.setEndDate(new Date());
        }
        contractRepository.save(contract);
        return ApiResponse.successResponse("contract status updated");
    }


    @Transactional
    @Override
    public ApiResponse deleteContract(UUID contractId) {
        Contract contract = contractRepository.findById(contractId).orElseThrow();
        contract.setStatus(ContractStatus.CANCELLED);
        contractRepository.save(contract);
        return ApiResponse.successResponse("Contract cancelled");
    }
}
