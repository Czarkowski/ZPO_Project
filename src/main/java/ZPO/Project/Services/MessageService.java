package ZPO.Project.Services;

import ZPO.Project.Models.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public Message generateMessage() {
        Message message = new Message();
        message.setContent("Hello from Spring Boot!");
        return message;
    }
}
