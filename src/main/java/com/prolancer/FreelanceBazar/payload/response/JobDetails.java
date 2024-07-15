package com.prolancer.FreelanceBazar.payload.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class JobDetails {
    private String title;
    private String description;
    private List<SkillResponse> skills;
}
