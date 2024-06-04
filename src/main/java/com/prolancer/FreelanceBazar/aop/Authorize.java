package com.prolancer.FreelanceBazar.aop;

import com.prolancer.FreelanceBazar.entity.enums.Permission;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface Authorize {
    Permission[] permissions() default {};
}
