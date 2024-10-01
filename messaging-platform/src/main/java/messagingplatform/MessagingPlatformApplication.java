package messagingplatform;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessagingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagingPlatformApplication.class, args);
	}

	// this listener keeps the app running, listens for messages from rabbitMQ
	@RabbitListener(queues = "queue")
	public void listen(String message) {
		System.out.println("Received message: " + message);
	}

}
