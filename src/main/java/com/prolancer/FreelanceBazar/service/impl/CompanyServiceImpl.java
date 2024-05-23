package com.prolancer.FreelanceBazar.service.impl;

import com.prolancer.FreelanceBazar.repository.CompanyRepository;
import com.prolancer.FreelanceBazar.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;



}
