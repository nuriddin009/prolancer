package com.prolancer.FreelanceBazar.payload.request;

import jakarta.validation.constraints.NotBlank;

public record AuthorizationRequest(
     @NotBlank String firstname,
     @NotBlank   String lastname,
     @NotBlank   String phoneNumber,
     @NotBlank   String email,
     @NotBlank   String password
) {
}
