package com.prolancer.FreelanceBazar.security;


import com.prolancer.FreelanceBazar.entity.User;
import com.prolancer.FreelanceBazar.entity.enums.Status;
import com.prolancer.FreelanceBazar.exceptions.UserNotActiveException;
import com.prolancer.FreelanceBazar.payload.model.UserManager;
import com.prolancer.FreelanceBazar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository repository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = repository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            if (user.getStatus().equals(Status.ACTIVE))
                return new UserManager(user);
            else
                throw new UserNotActiveException("User status not active");
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}