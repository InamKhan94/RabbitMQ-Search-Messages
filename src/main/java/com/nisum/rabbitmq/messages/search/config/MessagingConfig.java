package com.nisum.rabbitmq.messages.search.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MessagingConfig {

    //create custom connection factory

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
	ConnectionFactory connectionFactory() {
	    //CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("amqps://stadfrea:lhtEYalWFqUEH23lM11bQhlLpqbN6Vxd@fox.rmq.cloudamqp.com/stadfrea");
        log.info("Creating Connection");
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        //cachingConnectionFactory.setUsername("stadfrea");
        //cachingConnectionFactory.setPassword("lhtEYalWFqUEH23lM11bQhlLpqbN6Vxd");

        return cachingConnectionFactory;
	}
}

