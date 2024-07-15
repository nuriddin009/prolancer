package com.prolancer.FreelanceBazar.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
public class HtmlFileService {

    private final SpringTemplateEngine templateEngine;

    public HtmlFileService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String confirmationHtmlContent(String email, String code) {
        Context context = new Context();
        context.setVariable("email", email);
        context.setVariable("code", code);
        return templateEngine.process("email-verification.html", context);
    }

    public String confirmationForgotPasswordHtmlContent(String email, String code) {
        Context context = new Context();
        context.setVariable("email", email);
        context.setVariable("code", code);
        return templateEngine.process("forgot-password.html", context);
    }
}
