package com.prolancer.FreelanceBazar.service.impl;

import com.prolancer.FreelanceBazar.entity.Payment;
import com.prolancer.FreelanceBazar.entity.User;
import com.prolancer.FreelanceBazar.exceptions.ResourceNotFoundException;
import com.prolancer.FreelanceBazar.filter.PaymentFilter;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.response.PaymentResponse;
import com.prolancer.FreelanceBazar.repository.PaymentRepository;
import com.prolancer.FreelanceBazar.repository.UserRepository;
import com.prolancer.FreelanceBazar.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;



    @Transactional(readOnly = true)
    @Override
    public ApiResponse getPaymentList(PaymentFilter filter) {

        Page<Payment> pagedPayments = paymentRepository.findAllByFilter(filter);
        List<PaymentResponse> paymentList = pagedPayments.getContent().stream().map(payment ->
                PaymentResponse.builder()
                        .amount(payment.getAmount())
                        .payType(payment.getPayType())
                        .createdBy(getFrom(payment.getCreatedBy()))
                        .build()
        ).toList();

        return ApiResponse.successResponse(paymentList, "Payment list");
    }

    private String getFrom(String email) {
        if (email.equals("anonymous") || email.equals("system"))
            throw new ResourceNotFoundException("Not found");
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found for this email -> " + email));
        return String.format("%s %s", user.getFirstname(), user.getLastname());
    }
}
