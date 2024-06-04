package com.prolancer.FreelanceBazar.service.impl;

import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.AuthenticationRequest;
import com.prolancer.FreelanceBazar.repository.UserRepository;
import com.prolancer.FreelanceBazar.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;


    @Override
    public ApiResponse signIn(AuthenticationRequest request) {


        return null;
    }
}
