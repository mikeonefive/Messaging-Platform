package messagingplatform.controller;

import messagingplatform.data.Message;
import messagingplatform.service.Producer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class SendMessageEndpoint {

    private final Producer messageProducer;

    public SendMessageEndpoint(Producer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping("/sendMessage")
    public Message sendMessage(@RequestBody Message message) {
        messageProducer.sendOneMessage(message);
        return message;
    }
}
