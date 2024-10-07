//package messagingplatform.service;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;
//import messagingplatform.data.Message;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@Service
//public class ConsumerMultipleThreadpools {
//
//    @Value("${spring.consumer.threadpool.threads.number}")
//    int numberOfThreadsInPool;
//
//    private static final Logger logger = LoggerFactory.getLogger(ConsumerMultipleThreadpools.class);
//
//    private ExecutorService threadPoolA;
//    private ExecutorService threadPoolB;
//    private ExecutorService threadPoolC;
//    private ExecutorService threadPoolDefault;
//
//    @PostConstruct
//    public void init() {
//        threadPoolA = Executors.newFixedThreadPool(numberOfThreadsInPool);
//        threadPoolB = Executors.newFixedThreadPool(numberOfThreadsInPool);
//        threadPoolC = Executors.newFixedThreadPool(numberOfThreadsInPool);
//        threadPoolDefault = Executors.newFixedThreadPool(numberOfThreadsInPool);
//    }
//
//    // use thread pool to consume messages, @RabbitListener automatically handles incoming messages from the queue
//    @RabbitListener(queues = {"${spring.rabbitmq.queue.name}"})
//    public void consumeMessage(Message message) {
//
//        // create task/job
//        Runnable task = () -> logMessage(message);
//
//        // submit message processing to the thread pool (one of the workers/threads will pick up the task/job and start it)
//        // this task is executed in the background while the program can listen for more incoming messages
//        // logging happens in a separate thread (thanks to the thread pool), main part of app that listens for new messages can keep working
//
//        char firstLetterRecipient = findMessageCategory(message);
//
//        switch (firstLetterRecipient) {
//            case 'A' -> threadPoolA.submit(task);
//            case 'B' -> threadPoolB.submit(task);
//            case 'C' -> threadPoolC.submit(task);
//            case '?' -> threadPoolDefault.submit(task);
//        }
//    }
//
//    public void logMessage (Message message) {
//        logger.info("Received message <- {}", message);
//    }
//
//    private char findMessageCategory(Message message) {
//        return switch (message.getRecipient().toUpperCase().charAt(0)) {
//            case 'A' -> 'A';
//            case 'B' -> 'B';
//            case 'C' -> 'C';
//            default -> '?';
//        };
//    }
//
//    @PreDestroy
//    public void cleanup() {
//        threadPoolA.shutdown();
//        threadPoolB.shutdown();
//        threadPoolC.shutdown();
//        threadPoolDefault.shutdown();
//    }
//}
//
//// When the Runnable is given to the thread pool (using threadPool.submit(task), that task (the logging of the message) is picked up by a new thread from the pool
//// This means that logging happens in this new thread, separate from the main thread that is listening for messages.
