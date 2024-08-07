package com.prolancer.FreelanceBazar.config.rabbit;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmailPayload implements RabbitMessagePayload {
    private String email;
    private String code;
    private boolean isForgot;
}
