package com.prolancer.FreelanceBazar.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmationRequest extends ResendRequest {
    private String code;
}
