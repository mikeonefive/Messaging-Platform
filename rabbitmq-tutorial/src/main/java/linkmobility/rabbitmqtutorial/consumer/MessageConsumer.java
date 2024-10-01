package linkmobility.rabbitmqtutorial.consumer;

import linkmobility.rabbitmqtutorial.colors.AnsiColors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);

    // annotation to read/consume message from the queue, this listener will listen to the specified queue
    @RabbitListener(queues = {"${spring.rabbitmq.queue.name}"})
    public void consumeMessage(String message) {
        logger.info(AnsiColors.GREEN +
                "Received message <- {}" +
                AnsiColors.RESET, message);
    }
}
