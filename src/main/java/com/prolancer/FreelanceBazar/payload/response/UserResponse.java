package com.prolancer.FreelanceBazar.payload.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserResponse {
    private UUID userId;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private List<String> roles;
}
