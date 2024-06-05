package com.prolancer.FreelanceBazar.mapper;

import com.prolancer.FreelanceBazar.entity.JobEntity;
import com.prolancer.FreelanceBazar.payload.request.JobCreateRequest;
import com.prolancer.FreelanceBazar.payload.request.UpdateJobRequest;
import com.prolancer.FreelanceBazar.payload.response.JobResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface JobMapper {

    JobEntity toEntity(JobCreateRequest request);
    void updateEntity(@MappingTarget JobEntity job, UpdateJobRequest request);
    JobResponse toResponse(JobEntity job);

}
