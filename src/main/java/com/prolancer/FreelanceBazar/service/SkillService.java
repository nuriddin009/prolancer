package com.prolancer.FreelanceBazar.service;

import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.SkillRequest;

import java.util.UUID;

public interface SkillService {
    ApiResponse getAllSkills(String search);

    ApiResponse updateSkill(UUID id, SkillRequest skillDetails);

    ApiResponse getSkillById(UUID id);

    ApiResponse createSkill(SkillRequest skill);

    ApiResponse deleteSkill(UUID id);
}
