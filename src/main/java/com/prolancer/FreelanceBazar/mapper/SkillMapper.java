package com.prolancer.FreelanceBazar.mapper;

import com.prolancer.FreelanceBazar.entity.Skill;
import com.prolancer.FreelanceBazar.payload.request.SkillRequest;
import com.prolancer.FreelanceBazar.payload.response.SkillResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    Skill toEntity(SkillRequest request);

    SkillResponse toResponse(Skill skill);

    void updateEntity(@MappingTarget Skill skill, SkillRequest request);
}
