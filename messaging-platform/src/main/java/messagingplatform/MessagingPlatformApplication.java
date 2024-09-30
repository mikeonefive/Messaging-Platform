package messagingplatform;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MessagingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagingPlatformApplication.class, args);
	}

	// declare the "myQueue" queue
	@Bean
	public Queue myQueue() {
		return new Queue("myQueue", true); // true means the queue is durable (will persist even after server restart)
	}

	// this listener keeps the app running, listens for messages from rabbitMQ
	@RabbitListener(queues = "myQueue")
	public void listen(String message) {
		System.out.println("Received message: " + message);
	}

}
