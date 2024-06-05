package com.prolancer.FreelanceBazar.payload.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JobResponse {
    private String title;
    private String description;
    private List<SkillResponse> skills;
}
