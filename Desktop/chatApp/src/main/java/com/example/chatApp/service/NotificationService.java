package com.example.chatApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

// This file implements a NotificationService for sending notifications using Redis


@Service
public class NotificationService {

    @Autowired  // RedisTemplate:- A Spring Data Redis utility for interacting with Redis, such as publishing messages.
    private RedisTemplate<String, Object> redisTemplate;  /*
             A generic Redis template for operations like sending data to a Redis topic.
                String: The type for keys in Redis.
                Object: The type for values in Redis.*/



    @Autowired
    private ChannelTopic topic;  // ChannelTopic: Represents a topic in Redis Pub/Sub (Publish/Subscribe) where the  messaging publish.

    public void sendNotification(String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);

        /*
            redisTemplate.convertAndSend:
                    Publishes a message to the given Redis topic.
                    topic.getTopic(): Retrieves the name of the topic to publish to.
                    message: The content of the notification.
         */
    }
}
