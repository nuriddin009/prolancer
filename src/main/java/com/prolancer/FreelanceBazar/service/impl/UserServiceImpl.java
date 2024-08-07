package com.prolancer.FreelanceBazar.service.impl;

import com.prolancer.FreelanceBazar.entity.User;
import com.prolancer.FreelanceBazar.exceptions.PasswordNotMatchedException;
import com.prolancer.FreelanceBazar.filter.UserFilter;
import com.prolancer.FreelanceBazar.mapper.UserMapper;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.ChangePasswordRequest;
import com.prolancer.FreelanceBazar.payload.response.GetMeResponse;
import com.prolancer.FreelanceBazar.payload.response.UserResponse;
import com.prolancer.FreelanceBazar.repository.UserRepository;
import com.prolancer.FreelanceBazar.repository.page.ResponsePage;
import com.prolancer.FreelanceBazar.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserSession userSession;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public ApiResponse changePassword(ChangePasswordRequest request) {
        User user = userSession.getUser();
        if (passwordEncoder.matches(user.getPassword(), request.currentPassword())) {
            if (request.newPassword().equals(request.confirmPassword())) {
                user.setPassword(passwordEncoder.encode(request.newPassword()));
                userRepository.save(user);
                return ApiResponse.successResponse("Password updated");
            }
            throw new PasswordNotMatchedException("Password not matched");
        }
        throw new PasswordNotMatchedException("Incorrect password");
    }

    @Override
    public ApiResponse getMe(HttpServletRequest request) {
        User user = userSession.getUser();
        return ApiResponse.successResponse(userMapper.toResponse(user), "ok");
    }

    @Cacheable(cacheNames = "users_list")
    @Override
    public ApiResponse getUsers(UserFilter filter) {
        ResponsePage<UserResponse> usersList = userRepository.findAllByFilter(filter);
        return ApiResponse.successResponse(usersList, "Users list");
    }
}
