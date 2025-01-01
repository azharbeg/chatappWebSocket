// Handle incoming messages from clients and broadcast them to the appropriate destinations.


package com.example.chatApp.controller;

import com.example.chatApp.model.Message;
import com.example.chatApp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class WebSocketController {

    @Autowired
    private MessageRepository messageRepository;

    @MessageMapping("/chat") // Maps to "/app/chat"
    @SendTo("/topic/messages") // Sends messages to all subscribers
    public Message sendMessage(Message message) {
        // save the message to the database
         messageRepository.save(message);
        return message;
    }
}
