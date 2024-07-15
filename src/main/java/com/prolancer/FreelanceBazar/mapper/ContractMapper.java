package com.prolancer.FreelanceBazar.mapper;

import com.prolancer.FreelanceBazar.entity.Contract;
import com.prolancer.FreelanceBazar.payload.request.ContractRequest;
import com.prolancer.FreelanceBazar.payload.response.ContractResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ContractMapper {
    Contract toEntity(ContractRequest request);

    @Mapping(target = "contractId", source = "id")
    ContractResponse toResponse(Contract contract);

    void updateEntity(@MappingTarget Contract contract, ContractRequest request);
}
