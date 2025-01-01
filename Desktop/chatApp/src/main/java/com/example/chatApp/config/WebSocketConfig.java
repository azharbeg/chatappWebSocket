
/*
    This code configures WebSocket messaging in a Spring Boot application
    using STOMP (Simple Text Oriented Messaging Protocol).
 */


package com.example.chatApp.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // Enables WebSocket message handling, backed by a message broker. This annotation is key to enabling the use of STOMP protocols for WebSocket communication.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { // This interface provides methods to configure the WebSocket messaging system.


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) { //This method configures the message broker, which handles message routing.


        // Enable a simple in-memory message broker
        config.enableSimpleBroker("/topic"); // For broadcasting messages
        config.setApplicationDestinationPrefixes("/app"); // Prefix for messages from client
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Register an endpoint for WebSocket connection
        // This method registers the WebSocket endpoint(s) that clients will use to establish a connection.
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*")
                .withSockJS();
    }


//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(new MyWebSocketHandler(), "/ws")
//                .setAllowedOrigins("http://localhost:63342");  // Allow only your frontend origin
//    }
}
