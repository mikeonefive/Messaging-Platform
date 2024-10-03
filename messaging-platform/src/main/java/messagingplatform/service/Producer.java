package messagingplatform.service;

import jakarta.annotation.PostConstruct;
import messagingplatform.data.Message;
import messagingplatform.service.generators.RandomPhraseGenerator;
import messagingplatform.service.generators.RandomWordGenerator;
import messagingplatform.service.generators.ReceiverNameGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class Producer {

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    @Value("${spring.producer.threadpool.threads.number}")
    private int numberOfThreadsInPool;

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    // RabbitTemplate (methods to send messages)
    private final RabbitTemplate rabbitTemplate;

    // thread pool for starting multiple threads that execute tasks at the same time
    private ExecutorService threadPool;

    @PostConstruct
    public void init() {
        threadPool = Executors.newFixedThreadPool(numberOfThreadsInPool);
    }

    public Producer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOneMessage(Message message) {
        logger.info("Sending message -> {}", message);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    public void startProducingMessages(int numberOfMessages) {
        Runnable createAndSendMessage = () -> {
            sendOneMessage(new Message(
                    UUID.randomUUID(),
                    RandomWordGenerator.generate(),
                    ReceiverNameGenerator.generate(),
                    RandomPhraseGenerator.generate()
            ));
        };

        // schedule sending messages using the thread pool
        for (int i = 0; i < numberOfMessages; i++) {
            threadPool.execute(createAndSendMessage);
        }
        threadPool.shutdown();
    }
}
