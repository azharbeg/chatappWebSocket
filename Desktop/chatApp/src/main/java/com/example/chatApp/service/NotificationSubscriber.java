
//This code implements a Redis Subscriber for handling messages received on a Redis Pub/Sub topic.

package com.example.chatApp.service;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service  // This class implements the MessageListener interface, which requires the onMessage method to be defined.
public class NotificationSubscriber implements MessageListener {

    @Override
    // This method is automatically invoked whenever a message is published to the subscribed Redis topic
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("Received notification: " + message.toString());
        // Add logic to handle notifications (e.g., send to WebSocket clients)
    }
}
