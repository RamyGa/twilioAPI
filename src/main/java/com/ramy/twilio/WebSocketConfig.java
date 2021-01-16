package com.ramy.twilio;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {//we’re configuring a message broker that will be used to route messages from one client to another.

        //The message broker broadcasts messages to all the connected clients who are subscribed to a particular topic.
        config.enableSimpleBroker("/topic");//defines the messages whose destination starts with the /topic should be routed to the message broker.


        config.setApplicationDestinationPrefixes("/app");// defines the messages whose destination starts with /app.
        // After processing the message, the controller will send it to the broker channel.
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { //we register a WebSocket endpoint that clients use to connect to our WebSocket server.
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
        //Notice the use of withSockJS() with the endpoint configuration. SockJS is used to enable fallback options for browsers that don’t support WebSocket.

        //The word STOMP in the method name shows it's a derivation of Spring framework's STOMP implementation. STOMP stands for
        // Simple Text Oriented Messaging Protocol. It is a messaging protocol that defines the format and rules for data exchange.

    }



}
