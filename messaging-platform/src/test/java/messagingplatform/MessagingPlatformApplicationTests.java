package messagingplatform;

import messagingplatform.data.Message;
import messagingplatform.service.Consumer;
import messagingplatform.service.Producer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootTest
class MessagingPlatformApplicationTests {

	@Autowired
	Producer producer;

	@SpyBean
	private Consumer consumer; // spy on the actual Consumer bean (to find out which messages have been consumed)

	@Test
	void ProduceAndConsume() throws InterruptedException {

		int numberOfMessages = 100;

		producer.startProducingMessages(numberOfMessages);
		// wait for messages to be consumed
		Thread.sleep(5000);

		Mockito.verify(consumer, Mockito.times(numberOfMessages)).consumeMessage(Mockito.any());

		// capture the actual messages that were consumed
		ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
		// we need that because capture() method is used in conjunction with Mockito.verify() to intercept and capture the arguments passed to a method
		Mockito.verify(consumer, Mockito.atLeastOnce()).consumeMessage(messageCaptor.capture());
		List<Message> consumedMessages = messageCaptor.getAllValues();

		Assertions.assertEquals(numberOfMessages, consumedMessages.size(), "Not all messages were consumed exactly once!");

		//check the unique IDs of all the messages so we can ensure not a single message was consumed more than once
		Set<UUID> consumedMessagesUniqueIds = consumedMessages.stream()
				.map(Message::getId)
				.collect(Collectors.toSet());

		// number of messages should match unique IDs size
		Assertions.assertEquals(numberOfMessages, consumedMessagesUniqueIds.size(), "Some messages were consumed more than once.");
	}
}
