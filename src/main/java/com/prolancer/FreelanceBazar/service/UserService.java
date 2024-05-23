package com.prolancer.FreelanceBazar.service;

import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.ChangePasswordRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    ApiResponse changePassword(ChangePasswordRequest request);

    ApiResponse getMe(HttpServletRequest request);
}
