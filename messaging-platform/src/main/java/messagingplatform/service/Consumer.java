package messagingplatform.service;

import messagingplatform.data.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = {"${spring.rabbitmq.queue.name}"})
    public void consumeMessage(Message message) {
        logger.info("Received message <- {}", message);
    }
}
