package com.prolancer.FreelanceBazar.service.impl;

import com.prolancer.FreelanceBazar.repository.PaymentRepository;
import com.prolancer.FreelanceBazar.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
}
