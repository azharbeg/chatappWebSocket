package com.example.chatApp.service;

import com.example.chatApp.model.Message;
import com.example.chatApp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


// The MessageService file centralizes business logic, enabling clean separation of concerns, reusability, and performance optimization through caching.

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Cacheable(value = "chatHistory", key = "#sender + ':' + #receiver")
    public List<Message> getChatHistory(String sender, String receiver) {
        return messageRepository.findBySenderAndReceiver(sender, receiver);
    }
}
