package messagingplatform.data;

import java.util.UUID;

public class Message {

    private UUID id;
    private String sender;
    private String recipient;
    private String content;

    public Message() {
    }

    public Message(UUID id, String sender, String recipient, String content) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
