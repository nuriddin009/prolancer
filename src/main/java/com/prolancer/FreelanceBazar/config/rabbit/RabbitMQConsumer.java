package com.prolancer.FreelanceBazar.config.rabbit;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.prolancer.FreelanceBazar.config.rabbit.RabbitMQProducer.*;

@Component
public class RabbitMQConsumer {


    @RabbitListener(bindings = @QueueBinding(value = @Queue(durable = "false", value = EMAIL_SEND_NOTIFICATION_MESSAGE),
            exchange = @Exchange(durable = "false", value = SEND_EMAIL_NOTIFICATION_EXCHANGE),
            key = EMAIL_SEND_NOTIFICATION_MESSAGE), containerFactory = "rabbitListenerContainerFactory")
    public void sendEmailMessage(EmailPayload emailPayload) {
//        mailSendService.sendConfirmRegister(emailPayload);
    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(durable = "false", value = FORGOT_PASSWORD_SEND_NOTIFICATION_MESSAGE),
            exchange = @Exchange(durable = "false", value = SEND_FORGOT_PASSWORD_EMAIL_EXCHANGE),
            key = FORGOT_PASSWORD_SEND_NOTIFICATION_MESSAGE), containerFactory = "rabbitListenerContainerFactory")
    public void sendForgotPassword(EmailPayload emailPayload) {
//        mailSendService.sendConfirmForgot(emailPayload);
    }
}
