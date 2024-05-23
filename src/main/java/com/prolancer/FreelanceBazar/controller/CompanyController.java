package com.prolancer.FreelanceBazar.controller;

import com.prolancer.FreelanceBazar.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/company")
public class CompanyController {
    private final CompanyService service;

}
