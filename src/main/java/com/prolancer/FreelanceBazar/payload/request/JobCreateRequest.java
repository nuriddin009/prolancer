package com.prolancer.FreelanceBazar.payload.request;

import com.prolancer.FreelanceBazar.entity.enums.JobType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class JobCreateRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private JobType jobType;
    private Integer estimatedHours;
    private Double hourlyRate;
    private Double budget;
    @NotEmpty
    private List<UUID> skillsId;

}
