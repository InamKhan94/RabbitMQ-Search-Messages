package com.nisum.rabbitmqsearchmessage.config;

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

    public static final String QUEUE = "QUEUE.1";
    public static final String EXCHANGE = "EXCHANGE.1";
    public static final String ROUTING_KEY = "ROUTING_KEY.1";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

   /* @Bean
    public AmqpTemplate template(CachingConnectionFactory connectionFactory) {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("amqp://stadfrea@3.87.19.10:5671/stadfrea");
		cachingConnectionFactory.setUsername("stadfrea");
		cachingConnectionFactory.setUsername("lhtEYalWFqUEH23lM11bQhlLpqbN6Vxd");
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
    */

    //create custom connection factory
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
//
//    @Bean
//    SimpleMessageListenerContainer container(
//            ConnectionFactory connectionFactory,
//            MessageListenerAdapter listenerAdapter) {
//        SimpleMessageListenerContainer container =
//                new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames(QUEUE);
//        container.setMessageListener(listenerAdapter);
//        return container;
//    }
//    @Bean
//    MessageListenerAdapter listenerAdapter(Receiver receiver) {
//        return new MessageListenerAdapter(receiver, "receiveMessage");
//    }
    //@Autowired
    //private CachingConnectionFactory rabbitConnectionFactory;

//    @Bean
//    public RabbitTemplate rubeExchangeTemplate() {
//        log.info("INNNN");
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("amqps://stadfrea:lhtEYalWFqUEH23lM11bQhlLpqbN6Vxd@fox.rmq.cloudamqp.com/stadfrea");
//        cachingConnectionFactory.setUsername("stadfrea");
//        cachingConnectionFactory.setPassword("lhtEYalWFqUEH23lM11bQhlLpqbN6Vxd");
//        RabbitTemplate r = new RabbitTemplate(cachingConnectionFactory);
//        r.setExchange("rmq.rube.exchange");
//        r.setRoutingKey("rube.key");
//        r.setConnectionFactory(cachingConnectionFactory);
//
//        return r;
//    }

    //create MessageListenerContainer using custom connection factory
	/*@Bean
    MessageListenerContainer messageListenerContainer() {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
		simpleMessageListenerContainer.setQueues(queue());
		simpleMessageListenerContainer.setMessageListener(new RabbitMQListner());
		return simpleMessageListenerContainer;

	}*/
}

/*@Service
class RabbitMQListner implements MessageListener {

    public void onMessage(Message message) {
        System.out.println("Consuming Message - " + new String(message.getBody()));
    }

}*/
