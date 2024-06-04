package com.prolancer.FreelanceBazar.controller;

import com.prolancer.FreelanceBazar.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/contract")
public class ContractController {

    private final ContractService service;

}
