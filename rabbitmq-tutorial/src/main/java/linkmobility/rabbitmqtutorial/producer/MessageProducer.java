package linkmobility.rabbitmqtutorial.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    // create a logger for this class
    // allows logger to be associated with this specific class, useful for identifying where log messages are coming from when analyzing logs
    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    // we use RabbitTemplate and its methods to send messages
    private final RabbitTemplate rabbitTemplate;

    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        // logger.info(String.format("Sending message: %s", message)); for better performance use next line
        logger.info("Sending message: {}", message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message); // send message to exchange and route it to the queue with the key
    }
}
