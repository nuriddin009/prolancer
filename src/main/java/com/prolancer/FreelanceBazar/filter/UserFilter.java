package com.prolancer.FreelanceBazar.filter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prolancer.FreelanceBazar.entity.enums.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;


@Getter
@Setter
@ApiModel(description = "Параметры фильтра")
@ToString(onlyExplicitlyIncluded = true, doNotUseGetters = true)
public class UserFilter extends PageFilter {
    @ApiModelProperty(value = "The Search Key filter")
    @ToString.Include
    private String search = "";

    @ApiModelProperty("Status")
    @ToString.Include
    private Status status;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public String getSearchForQuery() {
        return StringUtils.isNotEmpty(search) ? "%" + search.toLowerCase().replace("_", "\\_") + "%" : search;
    }
}
