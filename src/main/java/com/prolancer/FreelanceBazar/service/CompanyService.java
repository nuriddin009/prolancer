package com.prolancer.FreelanceBazar.service;

import com.prolancer.FreelanceBazar.filter.CompanyFilter;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.CompanyRequest;

public interface CompanyService {
    ApiResponse getCompanies(CompanyFilter filter);

    ApiResponse regCompany(CompanyRequest request);
}
