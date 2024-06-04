package com.prolancer.FreelanceBazar.exceptions;

public class PasswordIncorrectException extends RuntimeException{
    public PasswordIncorrectException(String message) {
        super(message);
    }
}
