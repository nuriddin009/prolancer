package com.prolancer.FreelanceBazar.controller;

import com.prolancer.FreelanceBazar.filter.UserFilter;
import com.prolancer.FreelanceBazar.payload.model.ApiResponse;
import com.prolancer.FreelanceBazar.payload.request.ChangePasswordRequest;
import com.prolancer.FreelanceBazar.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService service;

    @PutMapping
    public ResponseEntity<ApiResponse> changePassword(@RequestBody ChangePasswordRequest request) {
        return ResponseEntity.ok(service.changePassword(request));
    }

    @GetMapping("me")
    public ResponseEntity<ApiResponse> getMe(HttpServletRequest request) {
        return ResponseEntity.ok(service.getMe(request));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getUsers(@ParameterObject UserFilter filter) {
        return ResponseEntity.ok(service.getUsers(filter));
    }


}
