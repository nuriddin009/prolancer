package com.prolancer.FreelanceBazar.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prolancer.FreelanceBazar.entity.enums.JobType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@ApiModel(description = "Параметры фильтра")
@ToString(onlyExplicitlyIncluded = true, doNotUseGetters = true)
public class JobFilter extends PageFilter {
    @ApiModelProperty(value = "The Search Key filter")
    @ToString.Include
    private String search = "";
    @ApiModelProperty(value = "job types")
    @ToString.Include
    private List<JobType> jobTypes;
    @ApiModelProperty(value = "get by hourly min price")
    @ToString.Include
    private BigDecimal hourlyMinPrice;
    @ApiModelProperty(value = "get by hourly max price")
    @ToString.Include
    private BigDecimal hourlyMaxPrice;
    @ApiModelProperty(value = "get by fixed min price")
    @ToString.Include
    private BigDecimal fixedMinPrice;
    @ApiModelProperty(value = "get by fixed max price")
    @ToString.Include
    private BigDecimal fixesMaxPrice;
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public String getSearchForQuery() {
        return StringUtils.isNotEmpty(search) ? "%" + search.toLowerCase().replace("_", "\\_") + "%" : search;
    }

}
