package com.prolancer.FreelanceBazar.payload.request;

import com.prolancer.FreelanceBazar.entity.enums.ProposalStatus;
import lombok.Data;

@Data
public class UpdateProposalRequest {

    private ProposalStatus status;


}
