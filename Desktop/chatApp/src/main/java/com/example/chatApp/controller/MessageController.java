/*
   --> Add an API to Retrieve Chat History
            Create a REST endpoint to fetch chat history for a specific conversation.

            ---------------------------------------------------------

            This code defines a REST controller in a Spring Boot application
          that manages messages for a chat application.
 */

package com.example.chatApp.controller;

import com.example.chatApp.model.Message;
import com.example.chatApp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired // @Autowired: Injects the MessageRepository bean, which handles database operations for Message entities.
    private MessageRepository messageRepository;


    // Purpose: Fetches messages exchanged between a sender and a receiver.
    @GetMapping("/conversation")
    public List<Message> getConversation(@RequestParam String sender, @RequestParam String receiver) {
        return messageRepository.findBySenderAndReceiver(sender, receiver);  // Calls findBySenderAndReceiver method in MessageRepository to query the database.
        // Returns a list of messages between the sender and receiver.
    }

    // Fetch all messages for a user
    @GetMapping("/{receiver}")  // Purpose: Fetches all messages sent to a specific user (receiver).
    public List<Message> getMessagesForReceiver(@PathVariable String receiver) {
        return messageRepository.findByReceiver(receiver);   // Calls findByReceiver method in MessageRepository to query the database.
        // Returns a list of messages for the receiver.
    }
}
