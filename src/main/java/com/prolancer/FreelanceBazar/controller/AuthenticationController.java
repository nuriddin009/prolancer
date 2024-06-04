package com.prolancer.FreelanceBazar.controller;

import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.AuthenticationRequest;
import com.prolancer.FreelanceBazar.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;


    @PostMapping("signIn")
    public ResponseEntity<ApiResponse> signIn(@RequestBody @Validated AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }

}
