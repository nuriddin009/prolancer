package com.prolancer.FreelanceBazar.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyFilter extends PageFilter {

    @ApiModelProperty(name = "search")
    private String search;



}
