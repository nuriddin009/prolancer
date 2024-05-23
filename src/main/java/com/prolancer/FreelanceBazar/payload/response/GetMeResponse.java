package com.prolancer.FreelanceBazar.payload.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetMeResponse {
    private String email;
    private String phoneNumber;
    private List<String> roles;
}
