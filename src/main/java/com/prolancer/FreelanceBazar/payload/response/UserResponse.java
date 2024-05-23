package com.prolancer.FreelanceBazar.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private String firstname;
    private String lastname;

    private List<String> roles;

}
