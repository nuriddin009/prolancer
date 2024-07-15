package com.prolancer.FreelanceBazar.payload.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class JobResponse {
    private UUID id;
    private String title;
    private String description;
    private List<SkillResponse> skills;
}
