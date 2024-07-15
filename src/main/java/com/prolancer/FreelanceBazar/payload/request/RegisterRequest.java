package com.prolancer.FreelanceBazar.payload.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank String firstname,
        @NotBlank String lastname,
        String phoneNumber,
        @NotBlank String email,
        @NotBlank String password

) {
}
