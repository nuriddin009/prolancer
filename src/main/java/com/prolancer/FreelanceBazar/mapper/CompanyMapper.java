package com.prolancer.FreelanceBazar.mapper;

import com.prolancer.FreelanceBazar.entity.Company;
import com.prolancer.FreelanceBazar.payload.request.CompanyRequest;
import com.prolancer.FreelanceBazar.payload.response.CompanyResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company toEntity(CompanyRequest request);

    CompanyResponse toResponse(Company company);
}
