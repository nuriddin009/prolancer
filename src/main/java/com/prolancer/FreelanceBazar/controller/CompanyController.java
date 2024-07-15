package com.prolancer.FreelanceBazar.controller;

import com.prolancer.FreelanceBazar.filter.CompanyFilter;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.CompanyRequest;
import com.prolancer.FreelanceBazar.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/company")
public class CompanyController {
    private final CompanyService service;


    @GetMapping
    public ResponseEntity<ApiResponse> getCompanies(@ParameterObject CompanyFilter filter) {
        return ResponseEntity.ok(service.getCompanies(filter));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> regCompany(@RequestBody CompanyRequest request) {
        return ResponseEntity.ok(service.regCompany(request));
    }
}
