package com.prolancer.FreelanceBazar.service.impl;

import com.prolancer.FreelanceBazar.repository.ProposalRepository;
import com.prolancer.FreelanceBazar.service.ProposalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProposalServiceImpl implements ProposalService {
    private final ProposalRepository proposalRepository;
    private final UserSession userSession;
}
