package com.prolancer.FreelanceBazar.payload.request;

import lombok.Data;

@Data
public class CardRequest {
    private String cardNumber;
    private String expirationMonth;
    private String expirationYear;
    private String cvc;
}
