package com.prolancer.FreelanceBazar.aop;

import com.prolancer.FreelanceBazar.entity.enums.Permission;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class LoggingAspect {


    @Before(value = "@annotation(authorize)")
    public void authorize(Authorize authorize) {
        Permission[] permissions = authorize.permissions();

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer "))
            throw new IllegalArgumentException("Token not found");




    }


}
