package com.prolancer.FreelanceBazar.repository.custom;

import com.prolancer.FreelanceBazar.filter.ContractFilter;
import com.prolancer.FreelanceBazar.payload.response.ContractResponse;
import com.prolancer.FreelanceBazar.repository.page.ResponsePage;

public interface CustomContractRepository {
    ResponsePage<ContractResponse> findAllContractsByFilter(ContractFilter filter);
}
