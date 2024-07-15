package com.prolancer.FreelanceBazar.controller;

import com.prolancer.FreelanceBazar.filter.PaymentFilter;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payment")
public class PaymentController {

    private final PaymentService service;


    @GetMapping
    public ResponseEntity<ApiResponse> getPaymentList(@ParameterObject PaymentFilter filter) {
        return ResponseEntity.ok(service.getPaymentList(filter));
    }




}
