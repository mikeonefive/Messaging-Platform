package messagingplatform;

import messagingplatform.service.Producer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class MessagingPlatformApplication {

	private final int numberOfMessages = 3;

	public static void main(String[] args) {
		SpringApplication.run(MessagingPlatformApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(Producer producer) {
		return args -> producer.startProducingMessages(numberOfMessages);
	}
}
