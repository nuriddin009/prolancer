package com.prolancer.FreelanceBazar.repository;

import com.prolancer.FreelanceBazar.entity.Payment;
import com.prolancer.FreelanceBazar.repository.custom.CustomPaymentRepository;

public interface PaymentRepository extends BaseRepository<Payment>, CustomPaymentRepository {
}
