package com.prolancer.FreelanceBazar.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging {
    private int page;
    private int pageLimit;
    private int pageSize;
    private long total;
}
