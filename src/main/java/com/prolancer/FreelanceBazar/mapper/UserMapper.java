package com.prolancer.FreelanceBazar.mapper;

import com.prolancer.FreelanceBazar.entity.User;
import com.prolancer.FreelanceBazar.payload.request.RegisterRequest;
import com.prolancer.FreelanceBazar.payload.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mappings({
            @Mapping(target = "userId", source = "id"),
            @Mapping(target = "roles", expression = "java(user.toRoleList())")
    })
    UserResponse toResponse(User user);

    @Mappings({
            @Mapping(target = "password", ignore = true)})
    User toEntity(RegisterRequest request);


}
