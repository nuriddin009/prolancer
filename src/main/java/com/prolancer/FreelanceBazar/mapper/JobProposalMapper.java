package com.prolancer.FreelanceBazar.mapper;

import com.prolancer.FreelanceBazar.entity.JobProposal;
import com.prolancer.FreelanceBazar.payload.request.ProposalRequest;
import com.prolancer.FreelanceBazar.payload.response.JobProposalResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface JobProposalMapper {
    JobProposal toEntity(ProposalRequest request);

    JobProposalResponse toResponse(JobProposal jobProposal);

    List<JobProposalResponse> toResList(List<JobProposal> proposals);
}
