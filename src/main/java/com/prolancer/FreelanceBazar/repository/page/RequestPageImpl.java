package com.prolancer.FreelanceBazar.repository.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestPageImpl implements RequestPage {

    private int pageNumber;
    private int pageLimit;
    private int startingIndex;

}
