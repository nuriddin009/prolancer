package com.prolancer.FreelanceBazar.payload.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SkillResponse {
    private UUID id;
    private String skillName;
}
