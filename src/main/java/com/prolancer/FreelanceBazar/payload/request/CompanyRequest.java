package com.prolancer.FreelanceBazar.payload.request;

import lombok.Data;

@Data
public class CompanyRequest {
    private String companyName;
    private String location;
    private double longitude;
    private double latitude;
}
