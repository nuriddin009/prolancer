package com.prolancer.FreelanceBazar.config.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.AmqpTimeoutException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public final static String EMAIL_SEND_NOTIFICATION_MESSAGE = "email_send_message_queue";
    public final static String SEND_EMAIL_NOTIFICATION_EXCHANGE = "send_email_notification_exchange";
    public final static String SEND_FORGOT_PASSWORD_EMAIL_EXCHANGE = "send_forgot_password_exchange";
    public final static String FORGOT_PASSWORD_SEND_NOTIFICATION_MESSAGE = "forgot_password_message_queue";


    public RabbitMQProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendNotificationToEmail(String email, String confirmationCode) {
        EmailPayload payload = new EmailPayload();
        payload.setEmail(email);
        payload.setCode(confirmationCode);
        send(payload, EMAIL_SEND_NOTIFICATION_MESSAGE, SEND_EMAIL_NOTIFICATION_EXCHANGE);
    }

    public void sendForgotPasswordEmail(String email, String confirmationCode) {
        EmailPayload payload = new EmailPayload();
        payload.setEmail(email);
        payload.setCode(confirmationCode);
        payload.setForgot(true);
        send(payload, FORGOT_PASSWORD_SEND_NOTIFICATION_MESSAGE, SEND_FORGOT_PASSWORD_EMAIL_EXCHANGE);
    }

    private void send(RabbitMessagePayload payload, String queue, String exchange) {
        try {
            String payloadString = objectMapper.writeValueAsString(payload);
            Message jsonMessage = MessageBuilder
                    .withBody(payloadString.getBytes())
                    .andProperties(
                            MessagePropertiesBuilder
                                    .newInstance()
                                    .setContentType("application/json")
                                    .setHeader("__TypeId__", TypeIdConstants.EMAIL_PAYLOAD)
                                    .build())
                    .build();

            rabbitTemplate.send(exchange, queue, jsonMessage);
        } catch (AmqpTimeoutException | AmqpConnectException exception) {
            exception.printStackTrace();
            log.error("Connection amqp error: {}", exception.getMessage());
        } catch (AmqpException | JsonProcessingException e) {
            log.error("Send to queue failed:", e);
        }
    }
}
