package com.prolancer.FreelanceBazar.service.impl;

import com.prolancer.FreelanceBazar.repository.ContractRepository;
import com.prolancer.FreelanceBazar.service.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;



}
