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

    @ApiModelProperty(value = "job type")
    @ToString.Include
    private JobType jobType;

    @ApiModelProperty(value = "min price")
    @ToString.Include
    private Double minPrice;

    @ApiModelProperty(value = "max price")
    @ToString.Include
    private Double maxPrice;

    

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public String getSearchForQuery() {
        return StringUtils.isNotEmpty(search) ? "%" + search.toLowerCase().replace("_", "\\_") + "%" : search;
    }
}
