package com.prolancer.FreelanceBazar.payload.request;

import lombok.Data;

@Data
public class PaymentRequest {
    public String source;
    public String amount;
    public String currency;
    public String description;
}
