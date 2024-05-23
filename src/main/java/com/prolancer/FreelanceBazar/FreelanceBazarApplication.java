package com.prolancer.FreelanceBazar;

import com.prolancer.FreelanceBazar.repository.impl.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;


@EnableAsync
@EnableJpaAuditing(auditorAwareRef = "securityAuditorAware")
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class FreelanceBazarApplication {
    public static void main(String[] args) {
        SpringApplication.run(FreelanceBazarApplication.class, args);
    }
}
