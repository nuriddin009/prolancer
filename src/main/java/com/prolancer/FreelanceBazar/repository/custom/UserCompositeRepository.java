package com.prolancer.FreelanceBazar.repository.custom;

import com.prolancer.FreelanceBazar.filter.UserFilter;
import com.prolancer.FreelanceBazar.payload.response.UserResponse;
import com.prolancer.FreelanceBazar.repository.page.ResponsePage;

public interface UserCompositeRepository {
    ResponsePage<UserResponse> findAllByFilter(UserFilter filter);
}
