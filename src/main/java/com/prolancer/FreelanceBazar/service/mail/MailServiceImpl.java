package com.prolancer.FreelanceBazar.service.mail;


import com.prolancer.FreelanceBazar.config.rabbit.EmailPayload;
import com.prolancer.FreelanceBazar.entity.User;
import com.prolancer.FreelanceBazar.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailSendService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JavaMailSender emailSender;
    private final HtmlFileService htmlFileService;
    private final UserRepository userRepository;


    @Override
    public void sendConfirmRegister(EmailPayload emailPayload) {
        User user = userRepository.findByEmail(emailPayload.getEmail()).orElseThrow();

        String htmlContent = htmlFileService.confirmationHtmlContent(emailPayload.getEmail(), emailPayload.getCode());
        sendEmail(htmlContent, emailPayload.getEmail(), "Your confirmation code for user", "static/images/header_logo.png", "header_logo", true);
    }

    @Override
    public void sendConfirmForgot(EmailPayload emailPayload) {
        User user = userRepository.findByEmail(emailPayload.getEmail()).orElseThrow();

        String htmlContent = htmlFileService.confirmationForgotPasswordHtmlContent(emailPayload.getEmail(), emailPayload.getCode());
        sendEmail(htmlContent, emailPayload.getEmail(), "Your confirmation code for forgot password", "static/images/header_logo.png", "header_logo", true);
    }

    private void sendEmail(String htmlContent, String receiverEmail, String subject, String imageUrl, String contentId, boolean multipart) {

        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, multipart);
            helper.setFrom("inoyatovnuriddin007@gmail.com");
            helper.setTo(receiverEmail);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            if (imageUrl != null && contentId != null) {
                ClassPathResource resource = new ClassPathResource(imageUrl);
                helper.addInline(contentId, resource);
            }
            emailSender.send(message);
            logger.info("Email sent.");
        } catch (MessagingException e) {
            logger.error("Send email error: {}", e.getMessage());
        }
    }
}
