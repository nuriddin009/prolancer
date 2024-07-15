package com.prolancer.FreelanceBazar.service;

import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.*;

public interface AuthenticationService {
    ApiResponse signIn(AuthenticationRequest request);

    ApiResponse register(RegisterRequest request);

    ApiResponse forgotPassword(ForgotPasswordRequest request);

    ApiResponse confirmationCode(ConfirmationRequest request);

    ApiResponse resendCode(ResendRequest request);
}
