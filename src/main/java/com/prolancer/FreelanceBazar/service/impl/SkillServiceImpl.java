package com.prolancer.FreelanceBazar.service.impl;

import com.prolancer.FreelanceBazar.entity.Skill;
import com.prolancer.FreelanceBazar.exceptions.ResourceNotFoundException;
import com.prolancer.FreelanceBazar.mapper.SkillMapper;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.SkillRequest;
import com.prolancer.FreelanceBazar.payload.response.SkillResponse;
import com.prolancer.FreelanceBazar.repository.SkillRepository;
import com.prolancer.FreelanceBazar.service.SkillService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    @Override
    public ApiResponse getAllSkills(String search) {
        return null;
    }

    @Transactional
    @Override
    public ApiResponse updateSkill(UUID id, SkillRequest skillDetails) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Skill not found for this id -> " + id));
        skillMapper.updateEntity(skill, skillDetails);
        return ApiResponse.successResponse("Skill updated");
    }

    @Override
    public ApiResponse getSkillById(UUID id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found for this id -> " + id));
        SkillResponse response = skillMapper.toResponse(skill);
        return ApiResponse.successResponse(response, "Skill");
    }

    @Transactional
    @Override
    public ApiResponse createSkill(SkillRequest request) {
        Skill entity = skillMapper.toEntity(request);
        skillRepository.save(entity);
        return ApiResponse.successResponse("New skill created");
    }

    @Transactional
    @Override
    public ApiResponse deleteSkill(UUID id) {
        skillRepository.deleteById(id);
        return ApiResponse.successResponse("Skill deleted");
    }
}
