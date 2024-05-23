package com.prolancer.FreelanceBazar.payload.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private Object data;
    private String message;
    private boolean success;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public ApiResponse(Object data, boolean success) {
        this.data = data;
        this.success = success;
    }


    public static ApiResponse successResponse(String message) {
        return new ApiResponse(message, true);
    }

    public static ApiResponse successResponse(Object data, String message) {
        return new ApiResponse(data, message, true);
    }

    public static ApiResponse errorResponse(String message) {
        return new ApiResponse(message, false);
    }
}
