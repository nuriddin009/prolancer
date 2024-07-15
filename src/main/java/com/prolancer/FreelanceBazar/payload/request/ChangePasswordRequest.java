package com.prolancer.FreelanceBazar.payload.request;


import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(
        @NotBlank(message = "Current password required") String currentPassword,
        @NotBlank(message = "Enter your new password") String newPassword,
        @NotBlank(message = "Confirmation mustn't be null") String confirmPassword
) {
}
