package com.prolancer.FreelanceBazar.controller;

import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.*;
import com.prolancer.FreelanceBazar.service.AuthenticationService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<ApiResponse> signUp(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("forgot_password")
    public ResponseEntity<ApiResponse> forgotPassword(@RequestBody @Validated ForgotPasswordRequest request) {
        return ResponseEntity.ok(authenticationService.forgotPassword(request));
    }

    @PostMapping("confirmation_code")
    public ResponseEntity<ApiResponse> confirmationCode(@RequestBody @Validated ConfirmationRequest request) {
        return ResponseEntity.ok(authenticationService.confirmationCode(request));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> resendCode(@RequestBody ResendRequest request) {
        return ResponseEntity.ok(authenticationService.resendCode(request));
    }


}
