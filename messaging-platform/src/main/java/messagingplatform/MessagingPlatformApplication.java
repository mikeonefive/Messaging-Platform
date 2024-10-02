package messagingplatform;

import messagingplatform.data.Message;
import messagingplatform.service.Producer;
import messagingplatform.service.generators.RandomPhraseGenerator;
import messagingplatform.service.generators.RandomWordGenerator;
import messagingplatform.service.generators.ReceiverNameGenerator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class MessagingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessagingPlatformApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(Producer producer) {
		return args -> producer.sendMessage(new Message(
				UUID.randomUUID(),
				RandomWordGenerator.generate(),
				ReceiverNameGenerator.generate(),
				RandomPhraseGenerator.generate()));
	}
}
