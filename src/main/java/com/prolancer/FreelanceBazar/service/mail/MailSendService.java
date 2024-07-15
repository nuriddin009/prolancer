package com.prolancer.FreelanceBazar.service.mail;


import com.prolancer.FreelanceBazar.config.rabbit.EmailPayload;

public interface MailSendService {

    void sendConfirmRegister(EmailPayload emailPayload);
    void sendConfirmForgot(EmailPayload emailPayload);
}
