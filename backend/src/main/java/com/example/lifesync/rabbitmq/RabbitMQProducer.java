package com.example.lifesync.rabbitmq;

import com.example.lifesync.email.EmailSendDTO;
import com.example.lifesync.email.EmailToken;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class RabbitMQProducer {

    private final Logger logger = Logger.getLogger(RabbitMQProducer.class.getName());

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routingkey.name}")
    private String routingKeyName;

    private final RabbitTemplate rabbitTemplate;

    public void send(EmailSendDTO emailToken) {
        logger.info(String.format("Message sent: %s", emailToken));
        rabbitTemplate.convertAndSend(exchangeName, routingKeyName, emailToken);
    }

}
