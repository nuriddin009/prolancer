package com.prolancer.FreelanceBazar.payload.model;

import com.prolancer.FreelanceBazar.payload.response.Paging;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedResponse<T> extends BaseResponse<T> {
    private Paging paging;
}
