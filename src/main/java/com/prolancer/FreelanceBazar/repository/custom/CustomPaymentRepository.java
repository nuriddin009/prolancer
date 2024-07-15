package com.prolancer.FreelanceBazar.repository.custom;

import com.prolancer.FreelanceBazar.entity.Payment;
import com.prolancer.FreelanceBazar.filter.PaymentFilter;
import org.springframework.data.domain.Page;

public interface CustomPaymentRepository {
    Page<Payment> findAllByFilter(PaymentFilter filter);
}
