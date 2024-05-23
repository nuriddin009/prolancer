package com.prolancer.FreelanceBazar.config;


import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("securityAuditorAware")
public class SecurityAuditorAware implements AuditorAware<String> {




    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication == null || !authentication.isAuthenticated()) && authentication.getPrincipal() != null) {
            return Optional.ofNullable((String) authentication.getPrincipal());
        }
        return Optional.of("system");
    }
}