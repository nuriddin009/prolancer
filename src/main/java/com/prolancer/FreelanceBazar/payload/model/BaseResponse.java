package com.prolancer.FreelanceBazar.payload.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@JsonPropertyOrder({
        "message",
        "localDateTime",
        "responseData",
        "responseDataList"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    @JsonIgnore
    private Boolean success;
    private String message;
    private LocalDateTime timeStamp;
    private T responseData;
    private Collection<T> responseDataList;

    public BaseResponse() {
        this.success = true;
        this.message = "Success";
        this.timeStamp = LocalDateTime.now();
    }

}