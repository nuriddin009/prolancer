package com.prolancer.FreelanceBazar.service;

import com.prolancer.FreelanceBazar.filter.PaymentFilter;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;

public interface PaymentService {
    ApiResponse getPaymentList(PaymentFilter filter);
}
