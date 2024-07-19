package com.prolancer.FreelanceBazar.service;

import com.prolancer.FreelanceBazar.filter.PaymentFilter;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.CardRequest;
import com.prolancer.FreelanceBazar.payload.request.PaymentRequest;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    ApiResponse getPaymentList(PaymentFilter filter);

    ResponseEntity<?> makePaymentUsingCard(String paymentMethodId, String customerId, long amount);

    ResponseEntity<?> addCard(CardRequest cardRequest);

    ResponseEntity<?> charge(PaymentRequest paymentRequest);
}
