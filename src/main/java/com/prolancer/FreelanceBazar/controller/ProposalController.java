package com.prolancer.FreelanceBazar.controller;

import com.prolancer.FreelanceBazar.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/proposal")
public class ProposalController {
    private final ProposalService service;


}
