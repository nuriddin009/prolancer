package com.prolancer.FreelanceBazar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PasswordNotMatchedException extends RuntimeException{
    public PasswordNotMatchedException(String message) {
        super(message);
    }
}
