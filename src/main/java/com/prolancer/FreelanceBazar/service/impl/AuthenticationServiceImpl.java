package com.prolancer.FreelanceBazar.service.impl;

import com.prolancer.FreelanceBazar.config.rabbit.RabbitMQProducer;
import com.prolancer.FreelanceBazar.entity.Role;
import com.prolancer.FreelanceBazar.entity.User;
import com.prolancer.FreelanceBazar.entity.enums.RoleName;
import com.prolancer.FreelanceBazar.entity.enums.Status;
import com.prolancer.FreelanceBazar.exceptions.PasswordNotMatchedException;
import com.prolancer.FreelanceBazar.exceptions.ResourceNotFoundException;
import com.prolancer.FreelanceBazar.mapper.UserMapper;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.model.UserManager;
import com.prolancer.FreelanceBazar.payload.request.*;
import com.prolancer.FreelanceBazar.payload.response.JwtResponse;
import com.prolancer.FreelanceBazar.payload.response.RegisterResponse;
import com.prolancer.FreelanceBazar.repository.RoleRepository;
import com.prolancer.FreelanceBazar.repository.UserRepository;
import com.prolancer.FreelanceBazar.security.JwtService;
import com.prolancer.FreelanceBazar.service.AuthenticationService;
import com.prolancer.FreelanceBazar.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final RabbitMQProducer rabbitMQProducer;


    @Override
    public ApiResponse signIn(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new ResourceNotFoundException("User not found with this email -> " + request.email()));
        UserManager userManager = new UserManager(user);
        String accessToken = jwtService.generateToken(userManager);
        String refreshToken = jwtService.generateRefreshToken(userManager);
        log.info("Authentication is successful");
        return ApiResponse.successResponse(new JwtResponse(accessToken, refreshToken), "Successfully logged");
    }

    @Transactional
    @Override
    public ApiResponse register(RegisterRequest request) {
        Role ROLE_USER = roleRepository.findByRoleName(RoleName.ROLE_USER);
        User entity = userMapper.toEntity(request);
        entity.setPassword(passwordEncoder.encode(request.password()));
        entity.setRoles(List.of(ROLE_USER));
        entity.setStatus(Status.CONFIRM);
        String code = getRandomCode();
        entity.setCode(code);
        userRepository.save(entity);
        log.info("User saved :  {}", entity.getId());
        rabbitMQProducer.sendNotificationToEmail(entity.getEmail(), code);
        return ApiResponse.successResponse(new RegisterResponse(entity.getEmail()),
                "User successfully registered");
    }

    @Override
    public ApiResponse forgotPassword(ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        if (StringUtils.isNotEmpty(user.getCode())) {
            if (request.getPassword().equals(request.getConfirmPassword())) {
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                user.setForgotPassword(false);
                userRepository.save(user);
                return ApiResponse.successResponse("User password updated");
            }else throw new PasswordNotMatchedException("Password non matched");
        }

        return new ApiResponse();
    }

    @Override
    public ApiResponse confirmationCode(ConfirmationRequest request) {

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();

        Instant instant = user.getUpdatedAt().atZone(ZoneId.systemDefault()).toInstant();

        if (DateUtils.isExpirationCode(Date.from(instant)))
            throw new IllegalArgumentException("Code was expired");

        if (!user.getCode().equals(request.getCode()))
            throw new IllegalArgumentException("Confirmation code is wrong");

        user.setCode(null);

        if (!request.isForgotPassword()) {
            user.setStatus(Status.ACTIVE);
        }else user.setForgotPassword(false);

        userRepository.save(user);

        return ApiResponse.successResponse("Congratulations you have successfully login");
    }

    @Override
    public ApiResponse resendCode(ResendRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String code = getRandomCode();
        user.setCode(code);
        user.setForgotPassword(request.isForgotPassword());
        userRepository.save(user);
        if (user.isForgotPassword())
            rabbitMQProducer.sendForgotPasswordEmail(user.getEmail(), code);
        else rabbitMQProducer.sendNotificationToEmail(user.getEmail(), code);

        return ApiResponse.successResponse("Check your email!!! Confirmation code was send");
    }


    private String getRandomCode() {
        Random random = new Random();
        return String.valueOf(random.nextInt(100000, 999999));
    }

}
