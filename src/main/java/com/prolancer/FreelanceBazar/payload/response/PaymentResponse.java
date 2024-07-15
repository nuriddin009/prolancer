package com.prolancer.FreelanceBazar.payload.response;

import com.prolancer.FreelanceBazar.entity.enums.PayType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentResponse {
    private BigDecimal amount;
    private PayType payType;
    private String createdBy;
    private LocalDateTime createdAt;
}
