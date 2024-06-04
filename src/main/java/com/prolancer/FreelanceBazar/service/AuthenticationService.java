package com.prolancer.FreelanceBazar.service;

import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.AuthenticationRequest;

public interface AuthenticationService {
    ApiResponse signIn(AuthenticationRequest request);
}
