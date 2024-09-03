package com.saieeshaguides.springboot.producer;

import com.saieeshaguides.springboot.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.queue.json.name}")
    private String jsonExchange;

    @Value("${rabbitmq.routingkey.json.name}")
    private String jsonRoutingKey;

    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);


    public void sendJsonMessage(User user){
        LOGGER.info(String.format("JSON message sent: -> %s", user.toString()));
        rabbitTemplate.convertAndSend(jsonExchange, jsonRoutingKey, user);
    }
}
