package com.prolancer.FreelanceBazar.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResendRequest {
    @NotBlank
    private String email;
    private boolean isForgotPassword;
}
