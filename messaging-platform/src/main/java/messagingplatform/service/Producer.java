package messagingplatform.service;

import messagingplatform.data.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    // RabbitTemplate and methods to send messages
    private final RabbitTemplate rabbitTemplate;

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Message message) {
        logger.info("Sending message -> {}", message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
