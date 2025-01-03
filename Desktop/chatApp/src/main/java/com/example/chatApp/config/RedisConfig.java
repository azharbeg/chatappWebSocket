package com.example.chatApp.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;


//   setting up Redis in a chat application

@Configuration
@EnableCaching   // this allows caching annotations like @Cacheable to work throughout the application.

public class RedisConfig {

    // Purpose: Provides a high-level abstraction for interacting with Redis. It simplifies common Redis operations like storing and retrieving data.
    //    RedisTemplate<String, Object>:-
    //                               A generic template for Redis operations where keys are strings and values can be objects.
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) { //RedisConnectionFactory connectionFactory:- Injected by Spring, this factory establishes connections to the Redis database.

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);  // Associates the connection factory with the Redis template, enabling it to execute commands.
        return template;
    }

    /*
        Purpose:-
            Defines a Redis topic for publish/subscribe messaging.
     */
     /*
        Usage:
            Messages published to this topic can be received by subscribers.
     */
    @Bean
    public ChannelTopic topic() { // ChannelTopic:- Represents a Redis topic. The string "chat:notifications" is the name of the topic used for communication.
        return new ChannelTopic("chat:notifications");
    }

    /*
            Purpose:->
                Configures a listener container for handling messages published to Redis topics.
     */
    @Bean  // RedisMessageListenerContainer:-  A Spring-managed container that listens for messages on Redis channels.
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory) { // connectionFactory:- The Redis connection factory used to connect the listener container to Redis.
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);  // This container can be configured to listen to multiple topics and pass messages to appropriate listeners.
        return container;
    }
}
