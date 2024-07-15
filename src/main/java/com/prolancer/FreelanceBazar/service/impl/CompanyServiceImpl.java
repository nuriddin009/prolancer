package com.prolancer.FreelanceBazar.service.impl;

import com.prolancer.FreelanceBazar.entity.Company;
import com.prolancer.FreelanceBazar.filter.CompanyFilter;
import com.prolancer.FreelanceBazar.mapper.CompanyMapper;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.CompanyRequest;
import com.prolancer.FreelanceBazar.payload.response.CompanyResponse;
import com.prolancer.FreelanceBazar.repository.CompanyRepository;
import com.prolancer.FreelanceBazar.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;


    @Override
    public ApiResponse getCompanies(CompanyFilter filter) {
        List<Company> companies = companyRepository.findAllNotDeleted();
        List<CompanyResponse> responseData = companies.stream().map(companyMapper::toResponse).toList();
        return ApiResponse.successResponse(responseData, "Company list");
    }

    @Transactional
    @Override
    public ApiResponse regCompany(CompanyRequest request) {
        Company company = companyMapper.toEntity(request);

        return new ApiResponse();
    }
}
